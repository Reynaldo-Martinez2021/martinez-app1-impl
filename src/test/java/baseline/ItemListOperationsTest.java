package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

class ItemListOperationsTest {
    //create an actual observableList
    ObservableList<Item> actualList = FXCollections.observableArrayList();

    @Test
    void setList() {
        //create a new item
        Item item = new Item();
        //set the variables
        item.setIsCompleted(false);
        item.setDueDate("2021-11-7");
        item.setDescription("Finish App1 Implementation");
        //add new item to actualList
        actualList.add(item);
        //create an observableList
        ObservableList<Item> returnList = FXCollections.observableArrayList();
        //use collections.addAll to fill up the returnList
        returnList.addAll(actualList);
        //assertEquals on returnList
        assertEquals(actualList, returnList);
    }

    @Test
    void addToList() {
        //create a new Item
        Item testing = new Item();
        //set the variables
        testing.setIsCompleted(false);
        testing.setDueDate("2021-11-7");
        testing.setDescription("Finish App1 Implementation");
        //boolean result
        boolean result = false;
        for (Item item : actualList) {
            if (item.getDescription().equals(testing.getDescription())) {
                //if it does exist call popup
                result = true;
            }
        }
        if(!result) {
            //will return true/false to calling method based on result
            actualList.add(testing);
        }
        //assertFalse
        assertFalse(result);
    }

    @Test
    void deleteFromList() {
        //create a testing list
        ObservableList<Item> testing = FXCollections.observableArrayList();
        testing.setAll(actualList);
        //create a new item to delete
        Item testingItem = new Item();
        //set the variables for item
        testingItem.setIsCompleted(false);
        testingItem.setDueDate("2021-11-7");
        testingItem.setDescription("Finish readMD");
        //add this list to observable list
        testing.add(testingItem);
        //now delete the new item
        testing.remove(testingItem);
        //assertEquals testing and observable
        assertEquals(actualList, testing);
    }
}