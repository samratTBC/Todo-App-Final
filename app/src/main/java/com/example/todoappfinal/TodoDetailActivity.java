package com.example.todoappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
    private int getMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        todo_desc_et = findViewById(R.id.todo_desc_et);
        todo_title_et = findViewById(R.id.todo_title_et);
        timePicker = findViewById(R.id.timePicker);
        check_btn = findViewById(R.id.tick_fab);

        todoViewModel= new ViewModelProvider(this).get(TodoViewModel.class);

        getMode = Integer.parseInt(getIntent().getStringExtra("Mode"));
        check_btn.setOnClickListener(check_btn_Listener);
        Log.d("TAG", "onCreate: " + getMode);

        if(getMode ==2)
        {
            String title = getIntent().getStringExtra("title");
            String desc = getIntent().getStringExtra("desc");

            todo_desc_et.setText(desc);
            todo_title_et.setText(title);
            timePicker.setText("111");
        }

    }

    private void updateModeOn() {
        int id = getIntent().getIntExtra("id",0);
        String title = todo_title_et.getText().toString();
        String desc = todo_desc_et.getText().toString();
        Boolean isComplete = getIntent().getBooleanExtra("isComplete", false);
        Todo todo = new Todo(id, title, desc, false, new Date());
        todoViewModel.updateTodo(todo);
        finish();
    }

    private View.OnClickListener check_btn_Listener = view -> {
      if( getMode == 1)
      {
          if(checkEmpty())
          {
              String title = todo_title_et.getText().toString();
              String desc = todo_desc_et.getText().toString();
              Todo todo = new Todo(0, title, desc, false, new Date());
              todoViewModel.insertTodo(todo);
              finish();
          }
          else
              Snackbar.make(this, view, "Please enter details.", Snackbar.LENGTH_SHORT).show();

      }
      else
      {
          updateModeOn();
      }


    };

    private boolean checkEmpty() {

        if((todo_desc_et.getText().toString()!= null && todo_desc_et.getText().toString()!="") && (todo_desc_et.getText().toString()!= null && todo_desc_et.getText().toString()!=""))
            return true;
        else
            return false;
    }
}