package baseline;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class TodoListApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        //create a setOnCloseRequest
        stage.setOnCloseRequest(e -> {
            //create instance of popUp
            PopUp pop = new PopUp();
            //check the returned value of popup
            if(pop.confirmCloseList()){
                //quit program if true
                Platform.exit();
                System.exit(0);
            }else{
                //else consume event
                e.consume();
            }
        });

        //create a parent root
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TaskApplication.fxml")));
        //attach scene graph to scene
        Scene scene = new Scene(root);
        //display in window's title bar
        stage.setTitle("My ToDoList");
        //attach scene to stage
        stage.setScene(scene);
        //display the stage
        stage.show();
    }

    public static void main(String[] args){
        //create an object and call its start method
        launch(args);
    }
}
