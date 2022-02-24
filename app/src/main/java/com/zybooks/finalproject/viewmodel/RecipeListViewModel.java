package com.zybooks.finalproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.repository.ApiRecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {



    private ApiRecipeRepository apiRecipeRepository;

    public RecipeListViewModel() {
        apiRecipeRepository = ApiRecipeRepository.getInstance();
    }

    public LiveData<List<RandomRecipeModel>> getRandomRecipe(){
        return apiRecipeRepository.getRecipe();
    }

    public LiveData<List<RandomRecipeModel>> getSearchRecipe(){
        return apiRecipeRepository.getSearchRecipe();
    }

    //calling method in repository
    public void searchRecipeApi(){
        apiRecipeRepository.searchRecipeApi();
    }

    public void searchSpecificRecipeApi(String query){
        apiRecipeRepository.searchSpecificRecipeApi(query);
    }
}
