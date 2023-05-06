package com.example.todoappfinal.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int todoID;

    private String todoTitle;

    @ColumnInfo(name = "is_complete")
    private Boolean isComplete;

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    private String todoDesc;

    private Date todoDateCreated;

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public Date getTodoDateCreated() {
        return todoDateCreated;
    }

    public void setTodoDateCreated(Date todoDateCreated) {
        this.todoDateCreated = todoDateCreated;
    }
}
