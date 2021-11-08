package baseline;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class TaskApplicationController implements Initializable {

    //create an itemObservableList
    ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

    //create a fileChooser
    FileChooser fileChooser;

    //create a stage
    Stage stage;

    //enable table menu
    TableViewUtils tableUtils;

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
        tableUtils = new TableViewUtils(tableView, itemObservableList);

        //set the tableView selection model
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //set the table row factory
        tableView.setRowFactory(param -> {
            //create a table row
            final TableRow<Item> row = new TableRow<>();
            //create a context menu
            final ContextMenu contextMenu = new ContextMenu();
            //add a menu option called remove
            final MenuItem removeMenuItem = new MenuItem("Remove");
            //have a set Action for the right click on the mouse
            removeMenuItem.setOnAction(event -> {
                //create an instance of itemListOperations
                ItemListOperations operations = new ItemListOperations();
                //delete the row from itemObservableList
                operations.deleteFromList(itemObservableList, row.getItem());
                //call the tableViewLoad function to repopulate the tableView
                tableViewLoad();
            });
            //add the removeMenu item
            contextMenu.getItems().add(removeMenuItem);
            //bind the row and context menu
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty()).then((ContextMenu)null).otherwise(contextMenu)
            );
            return row;
        });

        //initialize completed Column and add cell factory for checkbox
        completedColumn.setCellValueFactory(cd -> cd.getValue().isCompletedProperty());
        //set table cell factory as a checkbox table cell
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));
        //set Edit Commit to the item's isCompleted variable
        completedColumn.setOnEditCommit((TableColumn.CellEditEvent<Item, Boolean> t) -> (t.getTableView().getItems().get(t.getTablePosition().getRow())).setIsCompleted(t.getNewValue()));

        //initialize description column and set Style to center
        descriptionColumn.setCellValueFactory(cd -> cd.getValue().descriptionProperty());
        //set the cell factory for editable cell
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set the text alignment to center
        descriptionColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        descriptionColumn.setOnEditCommit((TableColumn.CellEditEvent<Item, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDescription(t.getNewValue()));

        //initialize dueDate column and set style sheet to center
        dueDateColumn.setCellValueFactory(cd -> cd.getValue().dueDateProperty());
        //set the cell factory for editable cell
        dueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //set the text alignment to center
        dueDateColumn.setStyle("-fx-alignment: CENTER;");
        //set the editOnStart to call validate edit
        dueDateColumn.setOnEditCommit((TableColumn.CellEditEvent<Item, String> t) -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDueDate(t.getNewValue()));

        //disable previous days on datePicker
        newItemDueDate.setValue(LocalDate.now());
        //disable past dates on the datePicker
        newItemDueDate.setDayCellFactory(d->
                new DateCell(){
                    @Override public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(LocalDate.now()));
                    }
                });

        fileChooser = new FileChooser();
    }

    //function to load tableView
    private void tableViewLoad(){
        //set the items to the tableView
        tableView.setItems(itemObservableList);
    }

    @FXML
    //this function is called upon when addButton is clicked
    public void addNewItem() {
        //call the createItem function to handle processing
        createItem(newItemDescription, newItemDueDate);
    }

    //this is called to create a new item
    void createItem(TextField description, DatePicker dueDate){
        //new item
        Item newItem;
        //create an instance of CreateItem
        CreateItem create = new CreateItem();
        //if the textField is empty pass in null value
        if(dueDate.getEditor().getText().isEmpty()){
            newItem = create.startProcess(description.getText(), null);
        }else{
            //pass in the dueDate value
            newItem = create.startProcess(description.getText(), dueDate.getValue());
        }
        //if the new item is not null add the new Item and clear text fields
        if(newItem != null) {
            //call the addToTableView with newItem as parameter
            addToTableView(newItem);
        }
    }

    //this is called to add a new item to table view
    void addToTableView(Item newItem){
        //create an instance of ItemListOperations
        ItemListOperations listOperations = new ItemListOperations();
        //call the addToList with observable list and newItem
        if(listOperations.addToList(itemObservableList, newItem)){
            //if it does get added to the list clear the textField
            newItemDescription.clear();
        }
        //call the tableViewLoad with the new updated Observable list
        tableViewLoad();
    }

    @FXML
    public void openFiles() {
        //this will call the function callMenuOptions passing the button text
        setUpFileChooserOpen();
    }

    @FXML
    public void saveFiles(){
        //this will call the function callMenuOptions passing the button text
        setUpFileChooserSave();
    }

    void setUpFileChooserSave(){
        //create an instance of menuOptions save
        MenuOptions menu = new MenuOptions();
        //create a stage from vBox and pass the stage to menuOptions
        stage = (Stage) vBox.getScene().getWindow();
        //set the name of file name
        fileChooser.setInitialFileName(taskTitle.getText());
        //set extension filter for text files
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        //add the extension
        fileChooser.getExtensionFilters().add(extensionFilter);
        //show the save dialog
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            menu.saveList(itemObservableList, file);
        }
    }

    void setUpFileChooserOpen(){
        //create an instance of menuOptions save
        MenuOptions menu = new MenuOptions();
        //create a stage from vBox and pass the stage to menuOptions
        stage = (Stage) vBox.getScene().getWindow();
        //show the open dialog
        File file = fileChooser.showOpenDialog(stage);
        //check if file is not null
        if(file != null){
            //if it is not then call menus openList
            menu.openList(tableView, itemObservableList, file);
            //check if fileName ends with .txt
            if(file.getName().endsWith(".txt")){
                //subtract the .txt for taskTitle
                taskTitle.setText(file.getName().substring(0, file.getName().length()-4));
            }
        }
    }
}