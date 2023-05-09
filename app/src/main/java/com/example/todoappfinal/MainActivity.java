package com.example.todoappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.todoappfinal.Fragment.TodoDetailFragment;
import com.example.todoappfinal.Fragment.TodoListFragment;

public class MainActivity extends AppCompatActivity {

    private Menu mainMenu;

    private ActionMode.Callback menuActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.main_menu,menu);
            actionMode.setTitle();

            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Performing fragment transaction Via the default fragment manager provided to the activity - getSupportFragmentManager
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,TodoListFragment.newInstance()).commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, TodoDetailFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mainMenu = menu;
        getMenuInflater().inflate(R.menu.main_menu,mainMenu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.todoDeleteMenu:

        }
        return super.onOptionsItemSelected(item);
    }
}