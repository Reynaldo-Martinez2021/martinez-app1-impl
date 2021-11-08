package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class TaskApplicationController implements Initializable {

    //create an itemObservableList
    private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

    //create a fileChooser
    private FileChooser fileChooser;

    //create a stage
    private Stage stage;

    //enable table menu
    private TableViewUtils tableUtils;

    @FXML
    private VBox vBox;

    @FXML
    private TextField taskTitle;

    @FXML // fx:id="tableView"
    private TableView<Item> tableView;

    @FXML // fx:id="completedColumn"
    private TableColumn<Item, Boolean> completedColumn;

    @FXML // fx:id="dueDateColumn"
    private TableColumn<Item, String> dueDateColumn;

    @FXML // fx:id="descriptionColumn"
    private TableColumn<Item, String> descriptionColumn;

    @FXML // fx:id="newItemDescription"
    private TextField newItemDescription;

    @FXML // fx:id="newItemDueDate"
    private DatePicker newItemDueDate;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //enable table menu

        //set the tableView selection model

        //set the table row factory
            //create a table row
            //create a context menu
            //add a menu option called remove
            //have a set Action for the right click on the mouse
                //create an instance of itemListOperations
                //delete the row from itemObservableList
                //call the tableViewLoad function to repopulate the tableView
            //add the removeMenu item
            //bind the row and context menu


        //initialize completed Column and add cell factory for checkbox
        //set table cell factory as a checkbox table cell
        //set Edit Commit to the item's isCompleted variable

        //initialize description column and set Style to center
        //set the cell factory for editable cell
        //set the text alignment to center
        //set the editOnStart to call validate edit

        //initialize dueDate column and set style sheet to center
        //set the cell factory for editable cell
        //set the text alignment to center
        //set the editOnStart to call validate edit

        //disable previous days on datePicker
        //disable past dates on the datePicker

    }

    //function to load tableView
    private void tableViewLoad(){
        //set the items to the tableView
    }

    @FXML
    //this function is called upon when addButton is clicked
    public void addNewItem() {
        //call the createItem function to handle processing
    }

    //this is called to create a new item
    private void createItem(TextField description, DatePicker dueDate){
        //new item
        //create an instance of CreateItem
        //if the textField is empty pass in null value
            //pass in the dueDate value
        //if the new item is not null add the new Item and clear text fields
            //call the addToTableView with newItem as parameter
    }

    //this is called to add a new item to table view
    private void addToTableView(Item newItem){
        //create an instance of ItemListOperations
        //call the addToList with observable list and newItem
            //if it does get added to the list clear the textField

        //call the tableViewLoad with the new updated Observable list
    }

    @FXML
    public void openFiles() {
        //this will call the function callMenuOptions passing the button text
    }

    @FXML
    public void saveFiles(){
        //this will call the function callMenuOptions passing the button text
    }

    private void setUpFileChooserSave(){
        //create an instance of menuOptions save
        //create a stage from vBox and pass the stage to menuOptions
        //set the name of file name
        //set extension filter for text files
        //add the extension
        //show the save dialog
        //if file isn't null call menu.saveList
    }

    private void setUpFileChooserOpen(){
        //create an instance of menuOptions save
        //create a stage from vBox and pass the stage to menuOptions
        //show the open dialog
        //check if file is not null
            //if it is not then call menus openList
            //check if fileName ends with .txt
            //subtract the .txt for taskTitle

    }
}
