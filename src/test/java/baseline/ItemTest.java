package baseline;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

class ItemTest {

    @Test
    void testDescription() {
        //create a actual string
        SimpleStringProperty actual = new SimpleStringProperty("Do homework");
        //create a new item
        Item testing = new Item();
        //set the description
        testing.setDescription("Do homework");
        //assertEquals actual with testing.getDescription
        assertEquals(actual.get(), testing.getDescription());
    }

    @Test
    void testDueDate() {
        //create an actual dueDate
        SimpleStringProperty actual = new SimpleStringProperty("2021/11/5");
        //create a new item
        Item testing = new Item();
        //set the due date
        testing.setDueDate("2021/11/5");
        //assertEquals actual with testing.getDueDate()
        assertEquals(actual.get(), testing.getDueDate());
    }


    @Test
    void testIsCompleted() {
        //create an actual boolean
        SimpleBooleanProperty actual = new SimpleBooleanProperty(false);
        //create a new item
        Item testing = new Item();
        //set the isCompleted to false
        testing.setIsCompleted(false);
        //assertFalse actual with testing.IsCompleted
        assertEquals(actual.get(),testing.getIsCompleted());
    }
}