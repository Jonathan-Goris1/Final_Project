package com.zybooks.finalproject.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.zybooks.finalproject.viewmodel.RecipeListViewModel;



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
                    Log.d(TAG, "onChanged: " + recipe.getImage());

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

    }
}