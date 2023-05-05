package com.example.todoappfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappfinal.POJO.Todo;
import com.example.todoappfinal.R;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Todo> todoArrayList;

    public TodoListAdapter(ArrayList<Todo> arrayList)
    {
        this.todoArrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_todo_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItems(todoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindItems(Todo todo) {
        }
    }
}
