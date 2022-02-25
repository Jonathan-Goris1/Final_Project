package com.zybooks.finalproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.repository.RecipeRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    //Creating repository variables and list variable

    private RecipeRepository repository;
    private LiveData<List<Recipe>> allRecipe;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        //Connecting the ViewModel to the repository
        repository = new RecipeRepository(application);
        allRecipe = repository.getAllRecipe();
    }

    //A get Method to return allRecipe List
    public LiveData<List<Recipe>> getAllRecipe() {
        return allRecipe;
    }

    public boolean setFavorite(boolean favorite){
        return favorite;
    }
}
