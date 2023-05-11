package com.example.todoappfinal.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoappfinal.Adapter.TodoListAdapter;
import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Listeners.CardViewListener;
import com.example.todoappfinal.MainActivity;
import com.example.todoappfinal.R;
import com.example.todoappfinal.TodoDetailActivity;
import com.example.todoappfinal.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TodoListFragment extends Fragment implements CardViewListener{


    private RecyclerView recyclerView;
    private TextView when_no_todo_tv;
    private FloatingActionButton addNewTodo;

    public TodoListAdapter adapter;

    private MainActivity mainActivity;
    
    private TextView searchEmpty;


    private TodoViewModel todoViewModel;

    private MenuItem menuItem;

    private SearchView searchView;
    private static Boolean linearGrid;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TodoListFragment() {
        // Required empty public constructor
    }

    public static TodoListFragment newInstance(Boolean grid)
    {
        linearGrid = grid;
        return new TodoListFragment();
    }

    public static TodoListFragment newInstance()
    {
        return new TodoListFragment();
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
        searchView=view.findViewById(R.id.search);
        searchEmpty=view.findViewById(R.id.searchEmpty);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(searchDatac(newText).size()==0)
                {
                    searchEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                else
                {
                    adapter.setDataForSearch(searchDatac(newText));
                    adapter.notifyDataSetChanged();
                    searchEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });



        mainActivity.getPopUpMenu().setOnClickListener( view1 -> {
            PopupMenu menu = new PopupMenu(getActivity(), mainActivity.getPopUpMenu());
            menu.getMenuInflater().inflate(R.menu.main_menu,menu.getMenu());

            menu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId())
                {
                    case R.id.todoDeleteMenu:
                    {
                        new AlertDialog.Builder(getContext())
                                .setMessage("Are you sure you want to delete all todos?")
                                .setPositiveButton("Delete", (dialog, which) -> {
                                    TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
                                    vm.deleteAll();
                                    adapter.notifyDataSetChanged();
                                })
                                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                })
                                .show();
                        break;
                    }
                    case R.id.changeGrid:
                    {
                        if (linearGrid) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            linearGrid = false;
                        } else {
                            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
                            linearGrid = true;
                        }
                        break;
                    }
                    case R.id.githubLink:
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://github.com/samratTBC/Todo-App-Final"));
                        startActivity(intent);
                        break;
                    }
                    default:
                        return true;
                }
                return true;
            });
            menu.show();

        });


    }

    private ArrayList<Todo> searchDatac(String newText) {
        ArrayList<Todo> mainData = (ArrayList<Todo>) todoViewModel.todoList.getValue();

        ArrayList<Todo> dataAfterSearch = new ArrayList<>();

        for (Todo todo : mainData) {
            if (todo.getTodoTitle().toLowerCase().contains(newText.toLowerCase())) {
                dataAfterSearch.add(todo);
            }
        }

        return dataAfterSearch;

    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    private void setData(View view) {

        todoViewModel.todoList.observe(getViewLifecycleOwner(),data->{

            if (data.size() == 0) {
                when_no_todo_tv.setVisibility(View.VISIBLE);
                searchEmpty.setVisibility(View.GONE);

            } else {
                when_no_todo_tv.setVisibility(View.GONE);
            }

            adapter = new TodoListAdapter(new ArrayList<>(data), this, mainActivity, getRecyclerView());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        });

    }

    View.OnClickListener newActivityFabListener = view -> {
        Intent intent = new Intent(view.getContext(), TodoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Mode", "1");
        intent.putExtras(bundle);
        startActivity(intent);
    };



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
        bundle.putString("notification", todo.getNotification());

        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onTodoChecked(com.example.todoappfinal.Database.Entity.Todo todo, Boolean checkValue, View view) {
        TodoViewModel vm = new ViewModelProvider(this).get(TodoViewModel.class);
        vm.updateTodo(new com.example.todoappfinal.Database.Entity.Todo(todo.getTodoID(),todo.getTodoTitle(),todo.getTodoDesc(), checkValue, todo.getTodoDateCreated() , todo.getNotification()));

        if(view instanceof CheckBox)
            Toast.makeText(getContext(), "Todo Completed", Toast.LENGTH_SHORT).show();
        if(view instanceof ImageView)
            Toast.makeText(getContext(), "Todo Pending.", Toast.LENGTH_SHORT).show();
    }


}