package com.example.todoappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Fragment.TodoDetailFragment;
import com.example.todoappfinal.Fragment.TodoListFragment;
import com.example.todoappfinal.Listeners.LongClickSelectedDelete;
import com.example.todoappfinal.ViewModel.TodoViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LongClickSelectedDelete {

    private ImageView deleteImageBtn;
    private ImageView popUpMenu;


    private ImageView cancelImg;

    private ImageView noteIconImg;

    public static Boolean linearGrid = false;


    public ImageView getCancelImg() {
        return cancelImg;
    }

    public ImageView getNoteIconImg() {
        return noteIconImg;
    }

    private View.OnClickListener popUpMenuListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,TodoListFragment.newInstance(linearGrid)).commit();
    }

    public void init()
    {
        deleteImageBtn = findViewById(R.id.deleteView);
        popUpMenu = findViewById(R.id.popupMenu);
//        popUpMenu.setOnClickListener(popUpMenuListener);
        cancelImg = findViewById(R.id.cancelSelection);
        noteIconImg=findViewById(R.id.noteIconImg);
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