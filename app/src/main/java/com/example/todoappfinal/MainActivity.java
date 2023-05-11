package com.example.todoappfinal;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.Database.Entity.Todo;

import com.example.todoappfinal.Fragment.TodoListFragment;
import com.example.todoappfinal.Listeners.LongClickSelectedDelete;
import com.example.todoappfinal.ViewModel.TodoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LongClickSelectedDelete {

    private ImageView deleteImageBtn;
    private ImageView popUpMenu;

    private TextView title_of_app;

    private ImageView cancelImg;

    private ImageView noteIconImg;

    public ImageView getCancelImg() {
        return cancelImg;
    }

    public ImageView getNoteIconImg() {
        return noteIconImg;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,TodoListFragment.newInstance()).commit();


    }



    public void init()
    {
        deleteImageBtn = findViewById(R.id.deleteView);
        popUpMenu = findViewById(R.id.popupMenu);
        cancelImg = findViewById(R.id.cancelSelection);
        noteIconImg=findViewById(R.id.noteIconImg);
        title_of_app = findViewById(R.id.title_of_app);

    }


    public ImageView getDeleteImageBtn()
    {
        return deleteImageBtn;
    }

    public ImageView getPopUpMenu() {
        return popUpMenu;
    }

    @Override
    public void deleteSelectedLongClick(ArrayList<Todo> lists, TodoListAdapter adapter) {
        TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        for(Todo list : lists)
        {
            vm.deleteTodo(list);
        }
        adapter.notifyDataSetChanged();
        deleteImageBtn.setVisibility(View.GONE);
        popUpMenu.setVisibility(View.VISIBLE);
    }





}