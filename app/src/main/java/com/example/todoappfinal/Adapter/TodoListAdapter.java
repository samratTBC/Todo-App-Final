package com.example.todoappfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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

        private TextView date_todo_init_card,desc_todo_card,title_todo_card;
        private CheckBox todo_complete_checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_todo_init_card = itemView.findViewById(R.id.date_todo_init_card);
            desc_todo_card = itemView.findViewById(R.id.desc_todo_card);
            title_todo_card = itemView.findViewById(R.id.title_todo_card);
            todo_complete_checkbox = itemView.findViewById(R.id.todo_complete_checkbox);

        }

        public void bindItems(Todo todo) {
            title_todo_card.setText(todo.getTodoTitle());
            String briefString = todo.getTodoDescription().length()>30? todo.getTodoDescription().substring(0,31): todo.getTodoDescription() + " ... ";
            desc_todo_card.setText(briefString);
            date_todo_init_card.setText(todo.getTodoStartDate().toString());
            todo_complete_checkbox.setChecked(todo.isCompleted());
        }
    }
}
