package com.example.todoappfinal.Listeners;

import android.view.View;

import com.example.todoappfinal.Database.Entity.Todo;

public interface CardViewListener  {

    void onTodoClicked(Todo todo);

    void onTodoChecked(Todo todo, Boolean checkValue, View view);

}
