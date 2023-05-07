package com.example.todoappfinal.Database.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoappfinal.Database.Dao.TodoDao;
import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Database.TodoRoomDB;


import java.util.List;

public class TodoRepository {
    public TodoDao todoDao;

    public LiveData<List<Todo>> todoList;

    public TodoDao getTodoDao() {
        return todoDao;
    }

    public void setTodoDao(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public LiveData<List<Todo>> getTodoList() {
        return todoList;
    }

    public void setTodoList(LiveData<List<Todo>> todoList) {
        this.todoList = todoList;
    }

    public TodoRepository(Application application)
    {
        TodoRoomDB databaseInstance = TodoRoomDB.getInstance(application);
        todoDao = databaseInstance.todoDao();
        todoList=todoDao.getTodos();
    }

    public void insertTodo(Todo todo)
    {
        todoDao.insertTodo(todo);
    }

    public void deleteTodo(Todo todo)
    {
        todoDao.deleteTodo(todo);
    }

    public void updateTodo(Todo todo)
    {
        todoDao.updateTodo(todo);
    }


    public void deleteAll()
    {
        todoDao.deleteAll();
    }



}
