package com.example.todoappfinal.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.POJO.Todo;
import com.example.todoappfinal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class TodoListFragment extends Fragment {


    private RecyclerView recyclerView;
    private TextView when_no_todo_tv;
    private FloatingActionButton addNewTodo;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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


        TodoListAdapter adapter = new TodoListAdapter(sendSomeData());

        if(sendSomeData().size()==0)
            when_no_todo_tv.setVisibility(View.VISIBLE);
        else
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(adapter);
        }

    }

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
}