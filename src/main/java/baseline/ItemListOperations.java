package baseline;

import javafx.collections.ObservableList;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class ItemListOperations {

    //create a function that will add new items to the list with item as parameter
    public boolean addToList(ObservableList<Item> controllerList, Item newItem) {
        //boolean result
        boolean result = false;
        for (Item item : controllerList) {
            if (item.getDescription().equals(newItem.getDescription())) {
                //create an instance of popup
                PopUp pop = new PopUp();
                pop.nonUniqueData();
                result = true;
            }
        }
        if(!result){
            controllerList.add(newItem);
            return true;
        }else{
            return false;
        }
    }

    //this function will delete items from the list with Item index as parameter
    public void deleteFromList(ObservableList<Item> controllerList, Item item){
        //takes the item as parameter
        controllerList.remove(item);
    }
}