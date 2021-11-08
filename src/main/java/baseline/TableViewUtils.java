package baseline;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class TableViewUtils {

    //create a final TableView variable
    private final TableView<Item> tableView;
    //create a itemObservable list to set values
    ObservableList<Item> itemObservableList;
    //create a private contextMenu
    private ContextMenu columnPopupMenu;
    //create additionalMenuItems list
    private final List<MenuItem> additionalMenuItems = new ArrayList<>();


    public TableViewUtils(TableView<Item> tableView, ObservableList<Item> list) {
        super();
        //set table view to passed tableview
        this.tableView = tableView;
        //set the itemObservableList to the passed list
        this.itemObservableList = list;

        //adding event and then implementing the event handler assignment
        tableView.sceneProperty().addListener(i -> tableView.getScene().windowProperty().addListener(i2 -> tableView.getScene().getWindow().setOnShown(i3 -> {
            tableView.tableMenuButtonVisibleProperty().addListener((ob, o, n) -> {
                if(Boolean.TRUE.equals(n)) {
                    registerListeners();
                }
            });
            if (tableView.isTableMenuButtonVisible()) {
                registerListeners();
            }
        })));
    }

    private void registerListeners() {
        final Node buttonNode = findButtonNode();

        // replace mouse listener on "+" node
        assert buttonNode != null;
        //calling method to show context menu when buttonNode is pressed
        buttonNode.setOnMousePressed(me -> {
            showContextMenu();
            me.consume();
        });

    }

    protected void showContextMenu() {
        final Node buttonNode = findButtonNode();

        setFixedHeader();

        // When the menu is already shown clicking the + button hides it.
        if (columnPopupMenu != null) {
            columnPopupMenu.hide();
        } else {
            // Show the menu
            final ContextMenu newColumnPopupMenu = createContextMenu();
            newColumnPopupMenu.setOnHidden(ev -> columnPopupMenu = null);
            columnPopupMenu = newColumnPopupMenu;
            columnPopupMenu.show(buttonNode, Side.BOTTOM, 0, 0);
            // Repositioning the menu to be aligned by its right side (keeping inside the table view)
            assert buttonNode != null;
            columnPopupMenu.setX(
                    buttonNode.localToScreen(buttonNode.getBoundsInLocal()).getMaxX() - columnPopupMenu.getWidth());
        }
    }

    private void setFixedHeader() {
        //setting the height of header
        Region tableHeaderRow = (Region) getTableHeaderRow();
        assert tableHeaderRow != null;
        //setting the default height
        double defaultHeight = tableHeaderRow.getHeight();
        //setting preferred height
        tableHeaderRow.setPrefHeight(defaultHeight);
    }

    private Node findButtonNode() {
        TableHeaderRow tableHeaderRow = (TableHeaderRow) getTableHeaderRow();
        if (tableHeaderRow == null) {
            return null;
        }

        for (Node child : tableHeaderRow.getChildren()) {

            // child identified as cornerRegion in TableHeaderRow.java
            if (child.getStyleClass().contains("show-hide-columns-button")) {
                return child;
            }
        }
        return null;
    }

    private Node getTableHeaderRow() {
        TableViewSkin<?> tableSkin = (TableViewSkin<?>) tableView.getSkin();
        if (tableSkin == null) {
            return null;
        }

        // get all children of the skin
        ObservableList<Node> children = tableSkin.getChildren();

        // find the TableHeaderRow child
        for (Node node : children) {
            if (node instanceof TableHeaderRow) {
                return node;
            }
        }
        return null;
    }

    private ContextMenu createContextMenu() {
        //create a new context menu
        ContextMenu cm = new ContextMenu();

        // create new context menu
        CustomMenuItem cmi;

        if (isShowAllColumnsOperators()) {
            // select all item
            Label selectAll = new Label("Select all");
            //add eventHandler and call doSelectAll
            selectAll.addEventHandler(MouseEvent.MOUSE_CLICKED, this::doSelectAll);
            //create customMenuItem
            cmi = new CustomMenuItem(selectAll);
            //on click call doSelect all
            cmi.setOnAction(this::doSelectAll);
            //set hideOnClick to false
            cmi.setHideOnClick(false);
            //add the cmi to tableView menu
            cm.getItems().add(cmi);

            // deselect all item
            Label deselectAll = new Label("Deselect all");
            //add an eventHandler and call doDeselectAll
            deselectAll.addEventHandler(MouseEvent.MOUSE_CLICKED, this::doDeselectAll);
            //create customerMenuItem
            cmi = new CustomMenuItem(deselectAll);
            //on click call the doDeselectAll
            cmi.setOnAction(this::doDeselectAll);
            //set hideOnClick to false
            cmi.setHideOnClick(false);
            //add the cmi to tableView menu
            cm.getItems().add(cmi);

            // separator
            cm.getItems().add(new SeparatorMenuItem());
        }

        // menu item for each of the available columns
        for (TableColumn<?,?> obj : tableView.getColumns()) {
            //creating checkBoxes for each column
            CheckBox cb = new CheckBox(obj.getText());
            //binding the selectedProperty with object visible property
            cb.selectedProperty().bindBidirectional(obj.visibleProperty());
            //create a new customMenuITem
            cmi = new CustomMenuItem(cb);
            //setOnAction the menuOption is selected
            cmi.setOnAction(e -> {
                cb.setSelected(!cb.isSelected());
                e.consume();
            });
            //set HidOnClick to false
            cmi.setHideOnClick(false);
            //add the cmi to menu option
            cm.getItems().add(cmi);
        }

        //create a separator
        cm.getItems().add(new SeparatorMenuItem());

        //create instance of is completed
        TaskViewingOptions viewingOptions = new TaskViewingOptions();

        //menu item for show completed
        CheckBox completedCb = new CheckBox("Completed Tasks");
        //add an eventListener
        completedCb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            //check is the event is selected
            if(completedCb.isSelected()) {
                //if it is call the displayComplete method
                viewingOptions.displayComplete(tableView, itemObservableList);
            }else {
                //set the tableView items
                tableView.setItems(itemObservableList);
            }
        });
        //create a new customMenuItem
        cmi = new CustomMenuItem(completedCb);
        //set HidOnClick to false
        cmi.setHideOnClick(false);
        //add the cmi to menu option
        cm.getItems().add(cmi);


        //menu item to show incomplete tasks
        CheckBox incompleteCb = new CheckBox("Incomplete Tasks");
        incompleteCb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            //check if incomplete checkBox is selected
            if(incompleteCb.isSelected()) {
                //if its not then call displayIncomplete
                viewingOptions.displayIncomplete(tableView, itemObservableList);
            }else {
                //set the tableView items
                tableView.setItems(itemObservableList);
            }
        });
        //create a new customMenuItem
        cmi = new CustomMenuItem(incompleteCb);
        //set HidOnClick to false
        cmi.setHideOnClick(false);
        //add the cmi to menu option
        cm.getItems().add(cmi);

        //create a separator
        cm.getItems().add(new SeparatorMenuItem());

        //create checkBox to clear the whole list
        CheckBox clearTableView = new CheckBox("Clear To-Do List");
        clearTableView.selectedProperty().addListener((observable, oldValue, newValue)-> {
            //check to see if the checkBox is selected
            if(clearTableView.isSelected()){
                //create an instance of Popup
                PopUp pop = new PopUp();
                if(pop.confirmClearList()) {
                    //returns true clear items
                    tableView.getItems().clear();
                }
            }
        });
        //create a new customMenuItem
        cmi = new CustomMenuItem(clearTableView);
        //set HidOnClick to false
        cmi.setHideOnClick(false);
        //add the cmi to menu option
        cm.getItems().add(cmi);

        if (!additionalMenuItems.isEmpty()) {
            cm.getItems().add(new SeparatorMenuItem());
            cm.getItems().addAll(additionalMenuItems);
        }

        return cm;
    }


    protected void doDeselectAll(Event e) {
        for (TableColumn<?,?> obj : tableView.getColumns()) {
            (obj).setVisible(false);
        }
        e.consume();
    }

    protected void doSelectAll(Event e) {
        for (TableColumn<?,?> obj : tableView.getColumns()) {
            (obj).setVisible(true);

        }
        e.consume();
    }

    public boolean isShowAllColumnsOperators() {
        //create boolean showColumns operators and set that value to true
        return true;
    }
}