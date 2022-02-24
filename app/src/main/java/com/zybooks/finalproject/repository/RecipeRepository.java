package com.zybooks.finalproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.database.RecipeDAO;
import com.zybooks.finalproject.database.RecipeDatabase;

import java.util.List;

public class RecipeRepository {
    //A TAG for logging purposes
    public static final String TAG = "RecipeRepository";
    //A list from RecipeSearchPost
    //Creating the DAO and list for databse
    private RecipeDAO recipeDao;
    private LiveData<List<Recipe>> allRecipe;
    //Instantiating the API


    public RecipeRepository(Application application) {
        //Creating the database
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDao = database.recipeDao();
        allRecipe = recipeDao.getAllRecipe();
    }
    public LiveData<List<Recipe>> getAllRecipe() {
        return allRecipe;
    }

    //A method for inserting into the database and using AsyncTask to run it in the background to execute it
    public void insert(Recipe recipe) {
        new InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }

    //A method for deleting record in the database and using AsyncTask to run it in the background to execute it
    public void delete(Recipe recipe) {
        new DeleteRecipeAsyncTask(recipeDao).execute(recipe);
    }

    //This Method does the inserting
    private static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDAO recipeDao;

        private InsertRecipeAsyncTask(RecipeDAO recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insert(recipes[0]);
            return null;
        }
    }

    //This Method does the deleting
    private static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDAO recipeDao;

        private DeleteRecipeAsyncTask(RecipeDAO recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            recipeDao.insert(recipes[0]);
            return null;
        }
    }
}

