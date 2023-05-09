package com.example.todoappfinal.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.Listeners.CardViewListener;
import com.example.todoappfinal.POJO.Todo;
import com.example.todoappfinal.R;
import com.example.todoappfinal.TodoDetailActivity;
import com.example.todoappfinal.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class TodoListFragment extends Fragment implements CardViewListener{


    private RecyclerView recyclerView;
    private TextView when_no_todo_tv;
    private FloatingActionButton addNewTodo;

    private TodoListAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TodoViewModel todoViewModel;

    public TodoListFragment() {
        // Required empty public constructor
    }
    public static TodoListFragment newInstance(String param1, String param2) {
        TodoListFragment fragment = new TodoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static TodoListFragment newInstance()
    {
        return new TodoListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        when_no_todo_tv = view.findViewById(R.id.when_no_todo_tv);
        addNewTodo = view.findViewById(R.id.add_todo_fb);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        setData(view);
        addNewTodo.setOnClickListener(newActivityFabListener);

    }



    private void setData(View view) {

        todoViewModel.todoList.observe(getViewLifecycleOwner(),data->{

            Log.d("TAG", "setData: Entered");
            if(sendSomeData().size()==0)
                when_no_todo_tv.setVisibility(View.VISIBLE);
            else
            {
                adapter = new TodoListAdapter(new ArrayList<>(data), this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }
        });


    }

    View.OnClickListener newActivityFabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), TodoDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Mode", "1");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };




    private ArrayList<Todo> sendSomeData() {

        ArrayList<Todo> data = new ArrayList<>();

        for(int i = 0; i<10; i++)
        {
            //int id, String todoTitle, String todoDescription, boolean completed, Date todoStartDate
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Todo todo = new Todo(i+1, "Title: " +(i+1), "Desc: " +(i+1), i%2==0?true:false, DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now()));
                data.add(todo);
            }
        }
        return data;
    }


    @Override
    public void onTodoClicked(com.example.todoappfinal.Database.Entity.Todo todo) {
        Intent intent = new Intent(getContext(), TodoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Mode", "2");

        bundle.putInt("id", todo.getTodoID());
        bundle.putString("desc", todo.getTodoDesc());
        bundle.putString("title", todo.getTodoTitle());
        bundle.putSerializable("date", todo.getTodoDateCreated().toString());
        bundle.putBoolean("isComplete", todo.getComplete());

        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onTodoChecked(com.example.todoappfinal.Database.Entity.Todo todo, Boolean checkValue, View view) {
        TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        vm.updateTodo(new com.example.todoappfinal.Database.Entity.Todo(todo.getTodoID(),todo.getTodoTitle(),todo.getTodoDesc(), checkValue, todo.getTodoDateCreated() ));

        if(view instanceof CheckBox)
            Toast.makeText(getContext(), "Todo Completed", Toast.LENGTH_SHORT).show();
        if(view instanceof ImageView)
            Toast.makeText(getContext(), "Todo Pending.", Toast.LENGTH_SHORT).show();
    }


}