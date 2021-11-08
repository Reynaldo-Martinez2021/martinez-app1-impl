package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class MenuOptions {

    public void openList(TableView<Item> tableView, ObservableList<Item> itemObservableList, File file){
        //create an observable list to store the contents of file list
        ObservableList<Item> openFileList = FXCollections.observableArrayList();
        //create string for bufferedReader
        String line;
        //create a try block with bufferedReader opening up file
        try(BufferedReader br =  new BufferedReader(new FileReader(file))){
            //create awhile loop to parse txt file
            while((line = br.readLine()) != null){
                //create a new item
                Item item = new Item();
                //parse each line into individual strings
                String[] tokens = line.split(",");
                //convert first string to boolean
                boolean isCompleted = Boolean.parseBoolean(tokens[0]);
                //set each token into each item variable
                item.setIsCompleted(isCompleted);
                item.setDueDate(tokens[1]);
                item.setDescription(tokens[2]);
                //add the item to the openFile list
                openFileList.add(item);
            }
            //clear the table view of items
            tableView.getItems().clear();
            //set the new list to the table view
            tableView.setItems(openFileList);
            //set the values to the itemObservableList
            itemObservableList.setAll(openFileList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveList(ObservableList<Item> itemList, File file){
        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            //create for loop to loop through items and build the string
            for(Item item : itemList) {
                //create a string for text file
                String text = item.getIsCompleted() + "," + item.getDueDate() + "," + item.getDescription() + ",\n";
                writer.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}