package com.example.todoappfinal.Database.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todoappfinal.POJO.DateConverter;

import java.util.Date;

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int todoID;

    @ColumnInfo(name = "todo_title")
    public String todoTitle;

    @ColumnInfo(name = "is_complete")
    public Boolean isComplete;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "todo_date_created")
    public Date todoDateCreated;

    @ColumnInfo(name = "todo_description")
    public String todoDesc;

    public Todo(int todoID, String todoTitle, String todoDesc, Boolean isComplete, Date todoDateCreated) {
        this.todoID = todoID;
        this.todoTitle = todoTitle;
        this.isComplete = isComplete;
        this.todoDateCreated = todoDateCreated;
        this.todoDesc = todoDesc;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }


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
