package com.example.todoappfinal.POJO;

import java.util.Date;

public class Todo {

    private int id;
    private String todoTitle;
    private String todoDescription;
    private boolean completed;
    private Date todoStartDate;

    public Todo() {
    }

    public Todo(int id, String todoTitle, String todoDescription, boolean completed, Date todoStartDate) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.completed = completed;
        this.todoStartDate = todoStartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getTodoStartDate() {
        return todoStartDate;
    }

    public void setTodoStartDate(Date todoStartDate) {
        this.todoStartDate = todoStartDate;
    }
}
