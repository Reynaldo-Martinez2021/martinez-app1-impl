package baseline;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PopUp {
    /*
     *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
     *  Copyright 2021 Reynaldo Martinez
     */

    //create a function that
    public void createPopup(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Invalid data");
        //set the content text
        alert.setContentText("Your description is invalid. The description must be not be empty and must stay under 256 characters.");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    public void nonUniqueData(){
        //create a new Alert with error alert type
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //set the title
        alert.setTitle("Invalid item description");
        //set the content text
        alert.setContentText("The current item description exists inside the list. Please enter a new description.");
        //use showAndWait to wait for user input
        alert.showAndWait();
    }

    public boolean confirmClearList(){
        //create a new Alert with confirmation alert type
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //set the title
        alert.setTitle("Confirmation");
        //set the content text
        alert.setContentText("Make sure to save your list before clearing. Once cleared there is not a way to recover the list. Press cancel if you don't wish to continue");
        //create a result button from the show and wait
        Optional<ButtonType> result = alert.showAndWait();
        //return the result to determine to continue or not
        return result.isPresent() && result.get() != ButtonType.CANCEL;
    }

    public boolean confirmCloseList(){
        //create a new Alert with confirmation alert type
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //set the title
        alert.setTitle("Confirmation");
        //set the content text
        alert.setContentText("Once exited any unsaved changes will be lost. Press okay to exit the program.");
        //create a result button from the show and wait
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isEmpty()){
            return false;
        } else return result.get() == ButtonType.OK;
    }
}