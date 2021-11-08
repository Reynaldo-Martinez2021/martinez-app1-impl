package baseline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */


class CreateItemTest{

    @Test
    void startProcess() {
        //create actual item
        Item actual = new Item();
        //set actual due date and description
        actual.setDueDate("2021-11-05");
        actual.setDescription("Finish App 1");
        actual.setIsCompleted(false);


        //create an instance of item
        Item testing = new Item();
        //create a string description
        String description = "Finish App 1";
        //create a string date
        LocalDate date = LocalDate.of(2021,11,5);


        //call validateDescription description length
        String returnDescription = validateDescription(description);
        //call the validateDate
        String returnDate = validateDate(date);


        //create if for setting newItem or popup
        if(returnDescription != null){
            //set the newItem values
            testing.setDescription(returnDescription);
            //set the newItem values
            testing.setDueDate(returnDate);
            //it reaches to here set completed to false
            testing.setIsCompleted(false);
        }

        //assertEquals both due date and description
        Assertions.assertEquals(actual.getDueDate(), testing.getDueDate());
        Assertions.assertEquals(actual.getDescription(), testing.getDescription());
        Assertions.assertEquals(actual.getIsCompleted(), testing.getIsCompleted());
    }


    String validateDescription(String description) {
        if(description == null || description.length() > 256 || description.isEmpty()){
            //create a variable to pass to another function for pop up description
            return null;
        }
        else{
            //set the description to the newItem
            return description;
        }
    }

    String validateDate(LocalDate date) {
        //check if matcher.matches
        if(date != null){
            return date.toString();
        }else{
            return "";
        }
    }
}