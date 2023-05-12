package com.example.todoappfinal.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Database.Repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public TodoRepository todoRepository;
    public LiveData<List<Todo>> todoList;

    public TodoViewModel(@NonNull Application application) {
        super(application);

        todoRepository = new TodoRepository(application);
        todoList=todoRepository.getTodoList();
    }


    public void insertTodo(Todo todo)
    {
        todoRepository.insertTodo(todo);
    }

    public void deleteTodo(Todo todo)
    {
        todoRepository.deleteTodo(todo);
    }

    public void updateTodo(Todo todo)
    {
        todoRepository.updateTodo(todo);
    }

    public void deleteAll()
    {
        todoRepository.deleteAll();
    }

    public LiveData<List<Todo>> sortByCompleted(){
        return todoRepository.sortByComplete();
    }

    public LiveData<List<Todo>> sortByInCompleted(){
        return todoRepository.sortByInComplete();
    }

}
