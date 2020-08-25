package com.practice.retrorxroommvvm.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.practice.retrorxroommvvm.model.Post;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PostDao postDao();

    private static volatile AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context) {

        if (appDataBase == null) {

            synchronized (AppDataBase.class) {

                appDataBase = Room.databaseBuilder(context,
                        AppDataBase.class, "AppDataBase").build();
            }

        }

        return appDataBase;

    }
}
