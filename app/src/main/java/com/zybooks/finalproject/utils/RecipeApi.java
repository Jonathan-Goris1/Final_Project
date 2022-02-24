package com.zybooks.finalproject.utils;

import com.zybooks.finalproject.model.GetRecipeResponse;
import com.zybooks.finalproject.model.RandomRecipeResponse;
import com.zybooks.finalproject.model.SearchRecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("recipes/random")
    Call<RandomRecipeResponse> getRandomRecipe(@Query("number") int number,
                                               @Query("apiKey") String api_key);

    @GET("recipes/complexSearch")
    Call<SearchRecipeResponse> getSearchRecipe(@Query("query") String searchQuery,
                                               @Query("number") int number,
                                               @Query("apiKey") String api_key);

    @GET("recipes/{id}/information")
    Call<GetRecipeResponse> getIdRecipe(@Path("id") int id,
                                        @Query("includeNutrition") Boolean nutrition,
                                        @Query("apiKey") String api_key);
}
