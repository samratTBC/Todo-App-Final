package com.example.todoappfinal.Database.Dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoappfinal.Entity.Todo;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoRoomDB extends RoomDatabase {

    private static TodoRoomDB roomDBInstance;
    private static String DB_NAME ="todo_DB";
    public abstract TodoDao todoDao();

    public synchronized static TodoRoomDB getInstance(Context context)
    {
        if(roomDBInstance == null)
        {
            roomDBInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoRoomDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }

        return roomDBInstance;
    }


}
