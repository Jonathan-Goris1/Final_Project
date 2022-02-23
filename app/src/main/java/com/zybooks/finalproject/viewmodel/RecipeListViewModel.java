package com.zybooks.finalproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zybooks.finalproject.model.RandomRecipeModel;
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

    //calling method in repository
    public void searchRecipeApi(){
        recipeRepository.searchRecipeApi();
    }
}
