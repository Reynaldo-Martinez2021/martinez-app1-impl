package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class TaskViewingOptions {

    //create a function that will display the incomplete task
    public void displayIncomplete(TableView<Item> tableView, ObservableList<Item> itemObservableList){
        //create an observable list to display Incomplete Items
        ObservableList<Item> incompleteItems = FXCollections.observableArrayList();
        //loop through itemObservableList
        for (Item item : itemObservableList) {
            //if item is false then add to incompleteItems
            if (!item.getIsCompleted()) {
                incompleteItems.add(item);
            }
        }
        //set incomplete list to the tableView
        tableView.setItems(incompleteItems);
    }

    public void displayComplete(TableView<Item> tableView, ObservableList<Item> itemObservableList){
        //create an observable list to display Completed Items
        ObservableList<Item> completedItems = FXCollections.observableArrayList();
        //loop through itemObservableList
        for (Item item : itemObservableList) {
            //if item isCompleted is true add to completedList
            if (item.getIsCompleted()) {
                completedItems.add(item);
            }
        }
        //set incomplete list to the tableView
        tableView.setItems(completedItems);
    }
}
