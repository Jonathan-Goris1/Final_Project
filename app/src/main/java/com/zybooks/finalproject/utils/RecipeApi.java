package com.zybooks.finalproject.utils;

import com.zybooks.finalproject.model.RandomRecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("recipes/random")
    Call<RandomRecipeResponse> getRandomRecipe(@Query("number") int number,
            @Query("apiKey") String api_key);

    @GET("recipes/complexSearch")
    Call<RandomRecipeResponse> getSearchRecipe(@Query("query") String searchQuery,
                                               @Query("apiKey") String api_key);
}
