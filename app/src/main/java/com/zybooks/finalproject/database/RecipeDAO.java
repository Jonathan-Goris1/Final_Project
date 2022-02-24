package com.zybooks.finalproject.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDAO {
    //handles inserts queries
    @Insert
    void insert(Recipe recipe);
    //handles delete queries
    @Delete
    void delete(Recipe recipe);
    //Query statement
    @Query("Select * from recipe_table")
    LiveData<List<Recipe>> getAllRecipe();
}

