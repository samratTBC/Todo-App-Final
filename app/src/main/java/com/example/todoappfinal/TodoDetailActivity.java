package com.example.todoappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.R;
import com.example.todoappfinal.ViewModel.TodoViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TodoDetailActivity extends AppCompatActivity {

    private TextInputEditText todo_title_et,todo_desc_et, timePicker;
    private TextView based_on_click_heading_tv;
    private FloatingActionButton check_btn;

    private ImageView back_btn_img;
    private TodoViewModel todoViewModel;
    private int getMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        getSupportActionBar().hide();
        todo_desc_et = findViewById(R.id.todo_desc_et);
        todo_title_et = findViewById(R.id.todo_title_et);
        timePicker = findViewById(R.id.timePicker);
        check_btn = findViewById(R.id.tick_fab);
        based_on_click_heading_tv = findViewById(R.id.based_on_click_heading_tv);
        back_btn_img = findViewById(R.id.back_btn_img);

        todoViewModel= new ViewModelProvider(this).get(TodoViewModel.class);

        getMode = Integer.parseInt(getIntent().getStringExtra("Mode"));
        check_btn.setOnClickListener(check_btn_Listener);
        Log.d("TAG", "onCreate: " + getMode);
        based_on_click_heading_tv.setText("CREATE MODE");

        if(getMode == 2)
        {
            String title = getIntent().getStringExtra("title");
            String desc = getIntent().getStringExtra("desc");
            String notif = getIntent().getStringExtra("notification");

            based_on_click_heading_tv.setText("UPDATE MODE");
            todo_desc_et.setText(desc);
            todo_title_et.setText(title);
            timePicker.setText(notif);
        }

        back_btn_img.setOnClickListener(view -> {
          finish();
        });

        timePicker.setOnClickListener(view -> {
            showDateTimePicker();
        });

    }

    private void showDateTimePicker() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);

        // Initialize views and set listeners
        final CalendarView calendarView = bottomSheetDialog.findViewById(R.id.calendar_view);
        final TimePicker timePickerr = bottomSheetDialog.findViewById(R.id.time_picker);
        Button saveButton = bottomSheetDialog.findViewById(R.id.save_button);
        Button cancelButton = bottomSheetDialog.findViewById(R.id.cancel_button);

        calendarView.setMinDate(System.currentTimeMillis() - 1000);

        // Set initial date and time on calendar and time picker
        final Calendar calendar = Calendar.getInstance();
        calendarView.setDate(calendar.getTimeInMillis(), false, true);
        timePickerr.setIs24HourView(true);
        timePickerr.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        timePickerr.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePickerr.setCurrentMinute(calendar.get(Calendar.MINUTE));

        // Set listener for date changes on calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Update the date on the calendar
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });

        // Set listeners for save and cancel buttons
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected time from time picker
                calendar.set(Calendar.HOUR_OF_DAY, timePickerr.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePickerr.getCurrentMinute());

                // Format date and time as desired
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String formattedDateTime = dateFormat.format(calendar.getTime());

                // Set the formatted date and time on the EditText
                timePicker.setText(formattedDateTime);

                // Dismiss the bottom sheet dialog
                bottomSheetDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(v -> {
            // Dismiss the bottom sheet dialog
            bottomSheetDialog.dismiss();
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }



    private void updateModeOn() {
        int id = getIntent().getIntExtra("id",0);
        String title = todo_title_et.getText().toString();
        String desc = todo_desc_et.getText().toString();
        String notification = timePicker.getText().toString();
        Boolean isComplete = getIntent().getBooleanExtra("isComplete", false);
        Todo todo = new Todo(id, title, desc, isComplete, new Date(),notification);
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
              String notif = timePicker.getText().toString();
              Todo todo = new Todo(0, title, desc, false, new Date(), notif);
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