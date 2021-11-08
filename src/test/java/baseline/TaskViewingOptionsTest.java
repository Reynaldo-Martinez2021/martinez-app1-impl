package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */


class TaskViewingOptionsTest {

    @Test
    void displayIncomplete() {
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

        //create an observable list to display Incomplete Items
        ObservableList<Item> incompleteItems = FXCollections.observableArrayList();
        //loop through itemObservableList
        for (Item item : actualList) {
            //if item is false then add to incompleteItems
            if (!item.getIsCompleted()) {
                incompleteItems.add(item);
            }
        }
        //assert that the itemList contains only 2 items
        assertEquals(2, incompleteItems.size());
    }

    @Test
    void displayComplete() {
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

        //create an observable list to display Completed Items
        ObservableList<Item> completedItems = FXCollections.observableArrayList();
        //loop through itemObservableList
        for (Item item : actualList) {
            //if item isCompleted is true add to completedList
            if (item.getIsCompleted()) {
                completedItems.add(item);
            }
        }
        //assertTrue that the completedItems will only contain 1 item
        assertEquals(1, completedItems.size());
    }
}