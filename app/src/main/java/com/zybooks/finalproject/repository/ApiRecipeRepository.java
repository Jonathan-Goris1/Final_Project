package com.zybooks.finalproject.repository;


import androidx.lifecycle.LiveData;

import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.network.RecipeApiClient;

import java.util.List;

public class ApiRecipeRepository {

    private static ApiRecipeRepository instance;

    private RecipeApiClient recipeApiClient;

    public static ApiRecipeRepository getInstance(){
        if(instance == null){
            instance = new ApiRecipeRepository();
        }
        return instance;
    }

    private ApiRecipeRepository(){
       recipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<RandomRecipeModel>> getRecipe(){

        return recipeApiClient.getRandomRecipe();

    }

    public LiveData<List<RandomRecipeModel>> getSearchRecipe(){
        return recipeApiClient.getSearchRecipe();
    }

    //Calling executor method

    public void searchRecipeApi(){
        recipeApiClient.searchRecipeApi();
    }

    public void searchSpecificRecipeApi(String query){

        recipeApiClient.searchSpecificRecipeApi(query);
    }
}
