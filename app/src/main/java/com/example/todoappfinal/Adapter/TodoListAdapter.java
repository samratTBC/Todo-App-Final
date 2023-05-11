package com.example.todoappfinal.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.todoappfinal.Listeners.LongClickSelectedDelete;
import com.example.todoappfinal.MainActivity;
import com.example.todoappfinal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {


    private ArrayList<Todo> searchTodo;
    public MainActivity context;
    private ArrayList<Todo> todoArrayList;

    private CardViewListener onclickCardListener;

    private LongClickSelectedDelete longClickSelectedDelete;

    private RecyclerView rv;
    private ArrayList<Todo> selectedList = new ArrayList<>();

    private Boolean selected = false;

    public void setDataForSearch(ArrayList<Todo>searchTodo)
    {
        this.todoArrayList=searchTodo;
    }

    public TodoListAdapter(ArrayList<Todo> arrayList,CardViewListener listener, MainActivity context, RecyclerView rv)
    {
        this.todoArrayList=arrayList;
        this.onclickCardListener=listener;
        this.context = context;
        this.longClickSelectedDelete =context;
        this.rv=rv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_todo_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItems(todoArrayList.get(position), holder);

    }

    public void unselect()
    {
        for (int i =0; i<getItemCount(); i++)
        {
            ViewHolder view = (ViewHolder) rv.getChildViewHolder(rv.getChildAt(i));
            view.text_holders.setBackgroundColor(Color.TRANSPARENT);
            Log.d("TAG", "unselect: " + rv.getChildAt(i).toString());
            Log.d("TAG", "unselect: " + view.toString());
        }
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

        public void bindItems(Todo todo, ViewHolder holder) {
            title_todo_card.setText(todo.getTodoTitle());
            String briefString = todo.getTodoDesc().length()<30? todo.getTodoDesc() : todo.getTodoDesc().substring(0,30) + " ... ";
            desc_todo_card.setText(briefString);
            date_todo_init_card.setText(formatDate(todo.getTodoDateCreated()));
            Log.d("DATE", "bindItems: "+ todo.getTodoDateCreated().toString());
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
                {
                    selected = false;
                    context.getDeleteImageBtn().setVisibility(View.GONE);
                    context.getPopUpMenu().setVisibility(View.VISIBLE);
                }

                context.getDeleteImageBtn().setVisibility(View.VISIBLE);
                context.getCancelImg().setVisibility(View.VISIBLE);
                context.getNoteIconImg().setVisibility(View.GONE);
                context.getPopUpMenu().setVisibility(View.GONE);

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
                    {
                        selected = false;
                        context.getDeleteImageBtn().setVisibility(View.GONE);
                        context.getPopUpMenu().setVisibility(View.VISIBLE);
                        context.getCancelImg().setVisibility(View.GONE);
                        context.getNoteIconImg().setVisibility(View.VISIBLE);
                    }

                }

                Log.d("TAG", "bindItems: " + selectedList.toString());
            });

            context.getDeleteImageBtn().setOnClickListener(view -> {
                longClickSelectedDelete.deleteSelectedLongClick(selectedList, TodoListAdapter.this);
                context.getCancelImg().setVisibility(View.GONE);
                context.getNoteIconImg().setVisibility(View.VISIBLE);
                notifyDataSetChanged();

            });

            context.getCancelImg().setOnClickListener(view -> {
                selected = false;

                unselect();
                selectedList.clear();

                context.getDeleteImageBtn().setVisibility(View.GONE);
                context.getPopUpMenu().setVisibility(View.VISIBLE);
                context.getCancelImg().setVisibility(View.GONE);
                context.getNoteIconImg().setVisibility(View.VISIBLE);
            });

        }


        private String formatDate(Date date_db) {
            //                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy", Locale.US);
////                SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
//                Date date = format.parse(date_db.toString());

            String date = date_db.toString();
            String formattedDate = date.substring(0, 3)+", "+date.substring(3, 10) + " " + date.substring(30,34);
            return formattedDate;
        }
    }
}
