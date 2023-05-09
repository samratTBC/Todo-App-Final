package com.example.todoappfinal.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappfinal.Database.Entity.Todo;
import com.example.todoappfinal.Listeners.CardViewListener;
import com.example.todoappfinal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Todo> todoArrayList;

    private CardViewListener onclickCardListener;

    private ArrayList<Todo> selectedList = new ArrayList<>();

    private Boolean selected = false;

    public TodoListAdapter(ArrayList<Todo> arrayList,CardViewListener listener)
    {
        this.todoArrayList=arrayList;
        this.onclickCardListener=listener;
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
        private ImageView completedImg;

        private ConstraintLayout cardView;

        private LinearLayout text_holders;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date_todo_init_card = itemView.findViewById(R.id.date_todo_init_card);
            desc_todo_card = itemView.findViewById(R.id.desc_todo_card);
            title_todo_card = itemView.findViewById(R.id.title_todo_card);
            todo_complete_checkbox = itemView.findViewById(R.id.todo_complete_checkbox);
            cardView = itemView.findViewById(R.id.card_view);
            completedImg = itemView.findViewById(R.id.completedImg);
            text_holders = itemView.findViewById(R.id.text_holders);

        }

        public void bindItems(Todo todo) {
            title_todo_card.setText(todo.getTodoTitle());
            String briefString = todo.getTodoDesc().length()>30? todo.getTodoDesc().substring(0,31): todo.getTodoDesc() + " ... ";
            desc_todo_card.setText(briefString);
            date_todo_init_card.setText(formatDate(todo.getTodoDateCreated()));
            if(todo.isComplete)
            {
                todo_complete_checkbox.setVisibility(View.GONE);
                completedImg.setVisibility(View.VISIBLE);
            }
            else {
                todo_complete_checkbox.setVisibility(View.VISIBLE);
                completedImg.setVisibility(View.GONE);
            }



            todo_complete_checkbox.setOnCheckedChangeListener((compoundButton, checkValue) -> {

                if (checkValue) {
                    title_todo_card.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    desc_todo_card.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    date_todo_init_card.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    title_todo_card.setPaintFlags(title_todo_card.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    desc_todo_card.setPaintFlags(desc_todo_card.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    date_todo_init_card.setPaintFlags(date_todo_init_card.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }

                onclickCardListener.onTodoChecked(todo,checkValue, todo_complete_checkbox);
            });

            completedImg.setOnClickListener(view -> {
                onclickCardListener.onTodoChecked(todo,false, completedImg);
            });

            cardView.setOnLongClickListener(view ->{
                selected = true;
                if(selectedList.contains(todoArrayList.get(getAdapterPosition())))
                {
                    text_holders.setBackgroundColor(Color.TRANSPARENT);
                    selectedList.remove(todoArrayList.get(getAdapterPosition()));
                }
                else
                {
                    text_holders.setBackgroundColor(itemView.getResources().getColor(R.color.selected));
                    selectedList.add(todoArrayList.get(getAdapterPosition()));
                }

                if(selectedList.size()==0)
                    selected = false;
                return true;
            });

            cardView.setOnClickListener(view -> {
                if(!selected)
                    onclickCardListener.onTodoClicked(todo);
                else
                {
                    if(selectedList.contains(todoArrayList.get(getAdapterPosition())))
                    {
                        text_holders.setBackgroundColor(Color.TRANSPARENT);
                        selectedList.remove(todoArrayList.get(getAdapterPosition()));
                    }
                    else
                    {
                        text_holders.setBackgroundColor(itemView.getResources().getColor(R.color.selected));
                        selectedList.add(todoArrayList.get(getAdapterPosition()));
                    }

                    if(selectedList.size()==0)
                        selected = false;
                }

                Log.d("TAG", "bindItems: " + selectedList.toString());
            });

        }

        private String formatDate(Date date_db) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yy");
                Date date = format.parse(date_db.toString());
                return format.format(date);
            }
            catch (ParseException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
