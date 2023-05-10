package com.example.todoappfinal.Listeners;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.Database.Entity.Todo;

import java.util.ArrayList;

public interface LongClickSelectedDelete {

    void deleteSelectedLongClick(ArrayList<Todo> list, TodoListAdapter adapter);

}
