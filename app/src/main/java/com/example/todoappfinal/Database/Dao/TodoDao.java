package com.example.todoappfinal.Database.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoappfinal.Database.Entity.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo_table")
    LiveData<List<Todo>> getTodos();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(Todo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo dao);

    @Query("SELECT * FROM todo_table ORDER by is_complete DESC")
    LiveData<List<Todo>> sortCompleted();

    @Query("SELECT * FROM todo_table ORDER by is_complete ASC")
    LiveData<List<Todo>> sortByIncomplete();


}
