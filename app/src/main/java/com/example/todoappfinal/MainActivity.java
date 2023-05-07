package com.example.todoappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.todoappfinal.Fragment.TodoDetailFragment;
import com.example.todoappfinal.Fragment.TodoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Performing fragment transaction Via the default fragment manager provided to the activity - getSupportFragmentManager
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,TodoListFragment.newInstance()).commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, TodoDetailFragment.newInstance()).commit();
    }



}