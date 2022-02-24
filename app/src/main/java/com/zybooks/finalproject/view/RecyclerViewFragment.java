package com.zybooks.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.finalproject.R;
import com.zybooks.finalproject.adapter.OnRecipeListener;
import com.zybooks.finalproject.adapter.RecipeRecyclerViewAdapter;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.model.SearchRecipeResponse;
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


public class RecyclerViewFragment extends Fragment implements OnRecipeListener {

    public static final String TAG = "Response";


    private RecyclerView myRecyclerView;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    private RecipeListViewModel recipeListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.response_recycler_view);

        ConfigureRecyclerView();
        //calling the observers
        ObserveAnyChange();
        // Inflate the layout for this fragment

        searchRecipeApi();
        return view;

    }

    private void ObserveAnyChange() {

        recipeListViewModel.getRandomRecipe().observe(getViewLifecycleOwner(), randomRecipeModels -> {
            //Observing any data change
            if (randomRecipeModels != null) {
                for (RandomRecipeModel recipe : randomRecipeModels) {
                    //Get the data in the log
                    recipeRecyclerViewAdapter.setmRecipe(randomRecipeModels);
                }
            }
        });
    }

    private void searchRecipeApi() {
        recipeListViewModel.searchRecipeApi();
    }


    private void ConfigureRecyclerView() {
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this);

        myRecyclerView.setAdapter(recipeRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }


    @Override
    public void onRecipeClick(int position) {
        Toast.makeText(getContext(), "The position =" + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("recipe",  recipeRecyclerViewAdapter.getSelectedRecipe(position));
        startActivity(intent);

    }

    private void GetRetrofitResponse(){
        RecipeApi recipeApi = RetrofitService.getRecipeApi();
        Call<SearchRecipeResponse> responseCall = recipeApi.getSearchRecipe("pizza", Credentials.NUMBER,Credentials.Api_Key);

        responseCall.enqueue(new Callback<SearchRecipeResponse>() {
            @Override
            public void onResponse(Call<SearchRecipeResponse> call, Response<SearchRecipeResponse> response) {
                if(response.code() == 200){
                    Log.d(TAG,"The response " + response.raw().toString());

                    List<RandomRecipeModel> recipe = new ArrayList<>(response.body().getSearchRecipe());
                    for(RandomRecipeModel recipies: recipe ){
                        Log.d(TAG, "onResponse: " + recipies.getTitle());
                    }
                }else{

                    try {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchRecipeResponse> call, Throwable t) {

            }
        });
    }
}