package com.example.todoappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.R;
import com.example.todoappfinal.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class TodoDetailActivity extends AppCompatActivity {

    private TextInputEditText todo_title_et,todo_desc_et, timePicker;
    private FloatingActionButton check_btn;

    private TodoViewModel todoViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);


        todo_desc_et = findViewById(R.id.todo_desc_et);
        todo_title_et = findViewById(R.id.todo_title_et);
        todo_title_et = findViewById(R.id.timePicker);
        check_btn = findViewById(R.id.tick_fab);

        todoViewModel= new ViewModelProvider(this).get(TodoViewModel.class);

        check_btn.setOnClickListener(check_btn_Listener);


    }

    private View.OnClickListener check_btn_Listener = view -> {
      if(!checkEmpty())
      {
            String title = todo_title_et.getText().toString();
            String desc = todo_desc_et.getText().toString();
            Todo todo = new Todo(0, title, desc, false, new Date());
            todoViewModel.insertTodo(todo);
            finish();
      }
      else
          Snackbar.make(this, view, "Please enter details.", Snackbar.LENGTH_SHORT).show();

    };

    private boolean checkEmpty() {
        return false;
    }
}