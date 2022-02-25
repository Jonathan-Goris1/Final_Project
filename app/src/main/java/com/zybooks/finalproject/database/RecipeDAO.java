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

    @Query("Delete from recipe_table Where id = :recipeId")
    void deleteRecipeId(int recipeId);
    //Query statement

    @Query("Select * from recipe_table Where id = :recipeId")
    int isDataExist(int recipeId);

    @Query("Select * from recipe_table")
    LiveData<List<Recipe>> getAllRecipe();
}

