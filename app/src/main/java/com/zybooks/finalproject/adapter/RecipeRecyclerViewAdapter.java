package com.zybooks.finalproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zybooks.finalproject.R;
import com.zybooks.finalproject.model.RandomRecipeModel;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RandomRecipeModel> mRecipe;
    private OnRecipeListener onRecipeListener;

    public RecipeRecyclerViewAdapter(OnRecipeListener onRecipeListener) {
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list, parent, false);
        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RecipeViewHolder)holder).title.setText(mRecipe.get(position).getTitle());

        Glide.with(((RecipeViewHolder) holder).image.getContext()).load(mRecipe.get(position).getImage()).into(((RecipeViewHolder)holder).image);

    }

    @Override
    public int getItemCount() {
        if(mRecipe != null){
            return mRecipe.size();
        }
        return 0;

    }

    public void setmRecipe(List<RandomRecipeModel> mRecipe) {
        this.mRecipe = mRecipe;
        notifyDataSetChanged();
    }

    public RandomRecipeModel getSelectedRecipe(int position){
        if(mRecipe != null){
            if(mRecipe.size() > 0){
                return mRecipe.get(position);
            }
        }
        return null;

    }
}
