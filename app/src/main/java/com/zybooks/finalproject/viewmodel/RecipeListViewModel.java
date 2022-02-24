package com.zybooks.finalproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.model.SearchRecipeResponse;
import com.zybooks.finalproject.repository.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {



    private RecipeRepository recipeRepository;

    public RecipeListViewModel() {
        recipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<RandomRecipeModel>> getRandomRecipe(){
        return recipeRepository.getRecipe();
    }

    public LiveData<List<RandomRecipeModel>> getSearchRecipe(){
        return recipeRepository.getSearchRecipe();
    }

    //calling method in repository
    public void searchRecipeApi(){
        recipeRepository.searchRecipeApi();
    }

    public void searchSpecificRecipeApi(String query){
        recipeRepository.searchSpecificRecipeApi(query);
    }
}
