package baseline;

import java.time.LocalDate;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class CreateItem{

    public Item startProcess(String desc, LocalDate date){
        //create an instance of Item
        Item newItem = new Item();
        //create instance of InvalidData
        PopUp popup = new PopUp();
        //call validateDescription and store in returnDescription
        String returnDescription = validateDescription(desc);
        //call the validateDate and store in returnDate
        String returnDate = validateDate(date);
        //check if the returnDescription is null
        if(returnDescription != null){
            //set the newItem values
            newItem.setDescription(returnDescription);
            //set the newItem values
            newItem.setDueDate(returnDate);
            //it reaches to here set completed to false
            newItem.setIsCompleted(false);
            //add newItem to list
            return newItem;
        } else{
            popup.createPopup();
            return null;
        }
    }
    //validate the description to check if its between 1-256 items
    private String validateDescription(String description){
        if(description == null || description.length() > 256 || description.isEmpty()){
            //create a variable to pass to another function for pop up description
            return null;
        } else{
            //set the description to the newItem
            return description;
        }
    }

    //validate the datePicker to check if its in gregorian format
    private String validateDate(LocalDate date) {
        //check if matcher.matches
        if(date != null){
            return date.toString();
        }else{
            return "";
        }
    }
}
