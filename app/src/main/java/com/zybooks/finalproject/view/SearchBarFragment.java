package com.zybooks.finalproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zybooks.finalproject.R;
import com.zybooks.finalproject.adapter.OnRecipeListener;
import com.zybooks.finalproject.adapter.RecipeRecyclerViewAdapter;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.viewmodel.RecipeListViewModel;


public class SearchBarFragment extends Fragment implements OnRecipeListener {

    public static final String TAG = "Response";


    private RecyclerView myRecyclerView;
    private RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    private RecipeListViewModel recipeListViewModel;
    TextInputEditText textInputEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_bar, container, false);
        recipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        textInputEditText = view.findViewById(R.id.search_textField);
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this);

        textInputEditText.setOnEditorActionListener(new TextInputEditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    // Inflate the layout for this fragment
                    searchSpecificRecipeApi(textInputEditText.getText().toString());
                    ObserveAnyChange();

                }
                return false;
            }
        });

        return view;

    }

    private void ObserveAnyChange() {

        recipeListViewModel.getSearchRecipe().observe(getViewLifecycleOwner(), randomRecipeModels -> {
            //Observing any data change
            if (randomRecipeModels != null) {
                for (RandomRecipeModel recipe : randomRecipeModels) {
                    Log.d(TAG, "onChanged: " + recipe.getTitle());
                    recipeRecyclerViewAdapter.setmRecipe(randomRecipeModels);
                }
            }
        });
    }

    private void searchSpecificRecipeApi(String query) {
        recipeListViewModel.searchSpecificRecipeApi(query);
    }


    private void ConfigureRecyclerView() {
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this);

        myRecyclerView.setAdapter(recipeRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }


    @Override
    public void onRecipeClick(int position) {
        Toast.makeText(getContext(), "The position =" + position, Toast.LENGTH_SHORT).show();

    }
}