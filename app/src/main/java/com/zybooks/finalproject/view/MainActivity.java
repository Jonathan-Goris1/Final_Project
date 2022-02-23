package com.zybooks.finalproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.zybooks.finalproject.R;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.model.RandomRecipeResponse;
import com.zybooks.finalproject.network.RetrofitService;
import com.zybooks.finalproject.utils.Credentials;
import com.zybooks.finalproject.utils.RecipeApi;
import com.zybooks.finalproject.viewmodel.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
public static final String TAG = "Response";
    Button btn;

    private RecipeListViewModel recipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

//        btn.setOnClickListener(view -> {
//
//            GetRetrofitResponse();
//        });
   }

    private void ObserveAnyChange(){

        recipeListViewModel.getRandomRecipe().observe(this, new Observer<List<RandomRecipeModel>>() {
            @Override
            public void onChanged(List<RandomRecipeModel> randomRecipeModels) {
                //Observing any data change
            }
        });
    }

    private void GetRetrofitResponse() {

        RecipeApi recipeApi = RetrofitService.getRecipeApi();

        Call<RandomRecipeResponse> responseCall = recipeApi.getRandomRecipe(Credentials.NUMBER, Credentials.Api_Key);

        responseCall.enqueue(new Callback<RandomRecipeResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeResponse> call, Response<RandomRecipeResponse> response) {
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "Raw " + response.raw().toString());

                    List<RandomRecipeModel> recipe = new ArrayList<>(response.body().getRandomRecipe());

                    for(RandomRecipeModel randomRecipeModel: recipe){
                        Log.d(TAG, "onResponse: " + randomRecipeModel.getTitle());
                    }
                }
                else{
                    try {
                        Log.d(TAG, "Error" + response.errorBody().string());
                        Log.d(TAG, "Error" + response.raw().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RandomRecipeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}