package com.zybooks.finalproject.adapter.database;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.finalproject.R;

public class DatabaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title;
    ImageView image;

    OnDatabaseListener onDatabaseListener;

    public DatabaseViewHolder(@NonNull View itemView, OnDatabaseListener onDatabaseListener) {
        super(itemView);

        this.onDatabaseListener = onDatabaseListener;
        title = itemView.findViewById(R.id.recipe_title);
        image = itemView.findViewById(R.id.recipe_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onDatabaseListener.onDatabaseRecipeClick(getAdapterPosition());
    }
}
