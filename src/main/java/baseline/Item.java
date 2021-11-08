package baseline;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Reynaldo Martinez
 */

public class Item{
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleStringProperty dueDate = new SimpleStringProperty();
    private final SimpleBooleanProperty isCompleted = new SimpleBooleanProperty();

    public SimpleBooleanProperty isCompletedProperty(){
        return isCompleted;
    }

    public SimpleStringProperty descriptionProperty(){
        return description;
    }

    public SimpleStringProperty dueDateProperty(){
        return dueDate;
    }


    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String desc) {
        description.set(desc);
    }


    public final String getDueDate() {
        return dueDate.get();
    }

    public final void setDueDate(String date) {
        dueDate.set(date);
    }


    public final boolean getIsCompleted() {
        return isCompleted.get();
    }

    public final void setIsCompleted(boolean completed) {
        isCompleted.set(completed);
    }
}
