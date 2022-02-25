package com.zybooks.finalproject.adapter.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zybooks.finalproject.R;
import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.model.RandomRecipeModel;

import java.util.List;

public class DatabaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> mRecipe;
    private OnDatabaseListener onDatabaseListener;

    public DatabaseRecyclerViewAdapter(OnDatabaseListener onDatabaseListener) {
        this.onDatabaseListener = onDatabaseListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list, parent, false);
        return new DatabaseViewHolder(view, onDatabaseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((DatabaseViewHolder)holder).title.setText(mRecipe.get(position).getTitle());

        Glide.with(((DatabaseViewHolder) holder)
                .image.getContext())
                .load(mRecipe.get(position).getImage())
                .centerCrop()
                .into(((DatabaseViewHolder)holder).image);

    }

    @Override
    public int getItemCount() {
        if(mRecipe != null){
            return mRecipe.size();
        }
        return 0;

    }

    public void setmRecipe(List<Recipe> mRecipe) {
        this.mRecipe = mRecipe;
        notifyDataSetChanged();
    }

    public Recipe getSelectedRecipe(int position){
        if(mRecipe != null){
            if(mRecipe.size() > 0){
                return mRecipe.get(position);
            }
        }
        return null;

    }
}
