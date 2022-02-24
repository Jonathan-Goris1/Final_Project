package com.zybooks.finalproject.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.zybooks.finalproject.R;
import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.viewmodel.DatabaseViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView recipeTitle, servingTime, description;
    private ImageView recipeImage;
    private DatabaseViewModel databaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recipeTitle = findViewById(R.id.recipe_title_textview);
        servingTime = findViewById(R.id.serving_textview);
        recipeImage = findViewById(R.id.recipe_image);
        description = findViewById(R.id.description_text);

        databaseViewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);
        //calling the getAllRecipe in the FavoriteViewModel class to place an observer
        databaseViewModel.getAllRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            //the observer will look for any change in the recipe list
            public void onChanged(List<Recipe> recipes) {

            }
        });


        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("recipe")) {
            StringBuilder newDescription = new StringBuilder();
            RandomRecipeModel randomRecipeModel = getIntent().getParcelableExtra("recipe");
            String[] descriptionList = randomRecipeModel.getSummary().split("<b>");

            for (String sentence : descriptionList) {
                newDescription.append(sentence).append(" ");
            }

            recipeTitle.setText(randomRecipeModel.getTitle());
            servingTime.setText("Cook in under " + randomRecipeModel.getReadyInMinutes() + "minutes");
            description.setText(newDescription.toString());

            Glide.with(this).load(randomRecipeModel.getImage()).into(recipeImage);

            Log.d("detailActivity", "GetDataFromIntent: " + randomRecipeModel.getSummary());
        } else {
            Log.d("detailActivity", "GetDataFromIntent: Nothing found ");
        }
    }
}