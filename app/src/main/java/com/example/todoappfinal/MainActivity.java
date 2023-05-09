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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Fragment.TodoDetailFragment;
import com.example.todoappfinal.Fragment.TodoListFragment;
import com.example.todoappfinal.Listeners.LongClickSelectedDelete;
import com.example.todoappfinal.ViewModel.TodoViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LongClickSelectedDelete {

    private ImageView deleteImageBtn;
    private ImageView popUpMenu;


    private View.OnClickListener popUpMenuListener = view -> {
        PopupMenu menu = new PopupMenu(MainActivity.this, popUpMenu);
        menu.getMenuInflater().inflate(R.menu.main_menu,menu.getMenu());

        menu.setOnMenuItemClickListener(menuItem -> {
            Toast.makeText(this, "You clicked" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        menu.show();

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        deleteImageBtn = findViewById(R.id.deleteView);
        popUpMenu = findViewById(R.id.popupMenu);
        popUpMenu.setOnClickListener(popUpMenuListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,TodoListFragment.newInstance()).commit();
    }

    public ImageView getDeleteImageBtn()
    {
        return deleteImageBtn;
    }

    public ImageView getPopUpMenu() {
        return popUpMenu;
    }

    @Override
    public void deleteSelectedLongClick(ArrayList<Todo> lists) {
        TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        for(Todo list : lists)
        {
            vm.deleteTodo(list);
        }
        deleteImageBtn.setVisibility(View.GONE);
        popUpMenu.setVisibility(View.VISIBLE);
    }





}