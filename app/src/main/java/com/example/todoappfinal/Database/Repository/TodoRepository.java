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

    public TodoRepository(Application application)
    {
        TodoRoomDB databaseInstance = TodoRoomDB.getInstance(application);
        todoDao = databaseInstance.todoDao();
        todoList=todoDao.getTodos();
    }

    void insertTodo(Todo todo)
    {
        todoDao.insertTodo(todo);
    }

    void deleteTodo(Todo todo)
    {
        todoDao.deleteTodo(todo);
    }

    void updateTodo(Todo todo)
    {
        todoDao.updateTodo(todo);
    }


    void deleteAll()
    {
        todoDao.deleteAll();
    }



}
