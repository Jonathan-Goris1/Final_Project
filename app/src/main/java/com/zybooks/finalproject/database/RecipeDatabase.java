package com.zybooks.finalproject.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class}, version = 5, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    //Creating an instance of the database
    private static RecipeDatabase instance;

    public abstract RecipeDAO recipeDao();

    public static synchronized RecipeDatabase getInstance(Context context){
        //building the database and returning the instance
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class, "recipe_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }




}
