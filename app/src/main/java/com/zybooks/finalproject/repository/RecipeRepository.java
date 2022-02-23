package com.zybooks.finalproject.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.network.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;

    private RecipeApiClient recipeApiClient;

    public static RecipeRepository getInstance(){
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository(){
       recipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<RandomRecipeModel>> getRecipe(){

        return recipeApiClient.getRecipe();
    }
}
