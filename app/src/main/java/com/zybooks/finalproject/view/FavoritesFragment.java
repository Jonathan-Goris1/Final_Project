package com.zybooks.finalproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.finalproject.R;
import com.zybooks.finalproject.adapter.database.DatabaseRecyclerViewAdapter;
import com.zybooks.finalproject.adapter.database.OnDatabaseListener;
import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.viewmodel.DatabaseViewModel;

import java.util.List;

public class FavoritesFragment extends Fragment implements OnDatabaseListener {

    private RecyclerView myRecyclerView;
    private DatabaseRecyclerViewAdapter databaseRecyclerViewAdapter;
    private DatabaseViewModel databaseViewModel;

    private LinearLayoutManager horizontalLayoutManager;

    private TextView favorite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        favorite = view.findViewById(R.id.favorite_textview);
        favorite.setVisibility(View.INVISIBLE);
        databaseViewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.favorite_recycler_view);
        horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        ConfigureRecyclerView();
        //calling the observers
        ObserveAnyChange();
        // Inflate the layout for this fragment

        return view;
    }

    private void ObserveAnyChange() {

        databaseViewModel.getAllRecipe().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                databaseRecyclerViewAdapter.setmRecipe(recipes);
                favorite.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ConfigureRecyclerView() {
        databaseRecyclerViewAdapter = new DatabaseRecyclerViewAdapter(this);

        myRecyclerView.setAdapter(databaseRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(horizontalLayoutManager);
    }

    @Override
    public void onDatabaseRecipeClick(int position) {
        //Toast.makeText(getContext(), "The position =" + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("database", databaseRecyclerViewAdapter.getSelectedRecipe(position));
        startActivity(intent);
    }
}