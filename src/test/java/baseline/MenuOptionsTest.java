package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

class MenuOptionsTest {

    @Test
    void openList() throws Exception {
        //create an actualList
        ObservableList<Item> actualList = FXCollections.observableArrayList();
        //create the items to fill actualList
        Item first = new Item();
        //set the first item
        first.setIsCompleted(false);
        first.setDueDate("2021-11-07");
        first.setDescription("Finish App 1 Implementation");
        //add to list
        actualList.add(first);
        //create the second item
        Item second = new Item();
        //set the second item
        second.setIsCompleted(false);
        second.setDueDate("2021-11-07");
        second.setDescription("Finish readMD");
        //add to list
        actualList.add(second);
        //create the third item
        Item third = new Item();
        //set the third item
        third.setIsCompleted(true);
        third.setDueDate("2021-11-07");
        third.setDescription("Take the dog for a walk");
        //add to list
        actualList.add(third);

        //create an observable list to store the contents of file list
        ObservableList<Item> openFileList = FXCollections.observableArrayList();
        //create string for bufferedReader
        String line;
        //create a try block with bufferedReader opening up file
        try(BufferedReader br =  new BufferedReader(new FileReader("testingFiles\\menuOptionsOpen.txt"))){
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
            //assert the actualList is equal to openFileList
            assertEquals(actualList.get(2).getIsCompleted(), openFileList.get(2).getIsCompleted());
            assertEquals(actualList.get(2).getDueDate(), openFileList.get(2).getDueDate());
            assertEquals(actualList.get(2).getDescription(), openFileList.get(2).getDescription());
        }
    }

    @Test
    void saveList() throws Exception{
        //create an actual string
        String actual = """
                false,2021-11-07,Finish App 1 Implementation,
                false,2021-11-07,Finish readMD,
                true,2021-11-07,Take the dog for a walk,
                """;

        //create an observableList
        ObservableList<Item> actualList = FXCollections.observableArrayList();
        //create the items to fill actualList
        Item first = new Item();
        //set the first item
        first.setIsCompleted(false);
        first.setDueDate("2021-11-07");
        first.setDescription("Finish App 1 Implementation");
        //add to list
        actualList.add(first);
        //create the second item
        Item second = new Item();
        //set the second item
        second.setIsCompleted(false);
        second.setDueDate("2021-11-07");
        second.setDescription("Finish readMD");
        //add to list
        actualList.add(second);
        //create the third item
        Item third = new Item();
        //set the third item
        third.setIsCompleted(true);
        third.setDueDate("2021-11-07");
        third.setDescription("Take the dog for a walk");
        //add to list
        actualList.add(third);

        //create a stringBuilder to test
        StringBuilder testingText = new StringBuilder();
        //create a try block with buffered writer
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testingFiles\\menuOptionsSave.txt"))){
            //create for loop to loop through items and build the string
            for(Item item : actualList) {
                //create a string for text file
                testingText.append(item.getIsCompleted()).append(",").append(item.getDueDate()).append(",").append(item.getDescription()).append(",\n");
                //create a string to add to text file
                String text = item.getIsCompleted() + "," + item.getDueDate() + "," + item.getDescription() + ",\n";
                //would call the write method to write to file
                writer.write(text);
            }
            //assertEquals the actualFile string and the string created
            assertEquals(actual, testingText.toString());
        }
    }
}