package com.zybooks.finalproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.zybooks.finalproject.R;
import com.zybooks.finalproject.database.Recipe;
import com.zybooks.finalproject.database.RecipeDatabase;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.viewmodel.DatabaseViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView recipeTitle, servingTime, description;
    private ImageView recipeImage;
    private DatabaseViewModel databaseViewModel;

    public static RecipeDatabase recipedatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recipeTitle = findViewById(R.id.recipe_title_textview);
        servingTime = findViewById(R.id.serving_textview);
        recipeImage = findViewById(R.id.recipe_image);
        description = findViewById(R.id.description_text);

        recipedatabase = Room.databaseBuilder(getApplicationContext(),RecipeDatabase.class,"recipe_table")
                .allowMainThreadQueries()
                .build();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        if(doesDataExist() || doesDataInExistDatabase()){
            menu.findItem(R.id.action_add).setIcon(R.drawable.ic_baseline_favorite_24);
            Log.d("TAG", "onCreateOptionsMenu: inside menu" );
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.action_add:
                if(doesDataExist() || doesDataInExistDatabase()){
                    item.setIcon(R.drawable.ic_baseline_favorite_border_24);
                    Log.d("TAG", "onOptionsItemSelected: deleted ");
                    deleteRecipe();
                    return true;
                }else{
                    Log.d("TAG", "onOptionsItemSelected: inserted ");
                    item.setIcon(R.drawable.ic_baseline_favorite_24);
                    insertRecipe();
                    return true;
                }

            case R.id.action_share:
                shareIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void shareIntent(){
        if(getIntent().hasExtra("recipe")){
        RandomRecipeModel randomRecipeModel = getIntent().getParcelableExtra("recipe");
        String url = randomRecipeModel.getSourceUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);}

        else if(getIntent().hasExtra("database")){
            Recipe randomRecipeModel = getIntent().getParcelableExtra("recipe");
            String url = randomRecipeModel.getSourceUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    private void GetDataFromIntent() {

        if (getIntent().hasExtra("recipe")) {
            RandomRecipeModel randomRecipeModel = getIntent().getParcelableExtra("recipe");
            String replaceA = randomRecipeModel.getSummary().replaceAll("<b>", " ");
            String replaceB = replaceA.replaceAll("</b>", " ");



            recipeTitle.setText(randomRecipeModel.getTitle());
            servingTime.setText("Cook in under " + randomRecipeModel.getReadyInMinutes() + " minutes");
            description.setText(getString(R.string.get_description, replaceB));

            Glide.with(this).load(randomRecipeModel.getImage()).into(recipeImage);

            Log.d("detailActivity", "GetDataFromIntent: " + randomRecipeModel.getSummary());


        } else if(getIntent().hasExtra("database")){
            Recipe recipe = getIntent().getParcelableExtra("database");
            String replaceA = recipe.getDescription().replaceAll("<b>", " ");
            String replaceB = replaceA.replaceAll("</b>", " ");
            recipeTitle.setText(recipe.getTitle());
            servingTime.setText("Cook in under " + recipe.getReadyInMinutes() + " minutes");


            Glide.with(this).load(recipe.getImage()).into(recipeImage);
            description.setText(getString(R.string.get_description, replaceB));
            Log.d("detailActivity", "GetDataFromIntent: Nothing found ");
        }
    }
    public boolean doesDataExist(){
        RandomRecipeModel recipe = getIntent().getParcelableExtra("recipe");
        int recipeId = 0;
        if(recipe != null)
            recipeId = recipe.getId();

        Log.d("TAG", "DataId: " + recipeId);
        int isDataExist = recipedatabase.recipeDao().isDataExist(recipeId);
        Log.d("TAG", "doesDataExist: " + isDataExist);
        try {
                if(isDataExist == 0){
                    Log.d("TAG", "not exist: ");
                    return false;
                }else {
                    Log.d("TAG", "exist: ");
                    return true;
                }


        }catch (Exception e) {
            //The only error we can get is if the recipe already exist in the database
            //if it does we simply catch it an notify the user
            e.printStackTrace();
            //A toast to notify the user that the recipe already exist in their favorites
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

        }
        return false;

    }

    public boolean doesDataInExistDatabase(){
        Recipe recipe = getIntent().getParcelableExtra("database");
        int recipeId = 0;
        if(recipe != null)
            recipeId = recipe.getId();
        Log.d("TAG", "DataId: " + recipeId);
        int isDataExist = recipedatabase.recipeDao().isDataExist(recipeId);
        Log.d("TAG", "doesDataExist: " + isDataExist);
        try {
            if(isDataExist == 0){
                Log.d("TAG", "not exist: ");
                return false;
            }else {
                Log.d("TAG", "exist: ");
                return true;
            }


        }catch (Exception e) {
            //The only error we can get is if the recipe already exist in the database
            //if it does we simply catch it an notify the user
            e.printStackTrace();
            //A toast to notify the user that the recipe already exist in their favorites
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

        }
        return false;

    }
    public void deleteRecipe(){
        try {
            if (getIntent().hasExtra("recipe")) {
                RandomRecipeModel recipe = getIntent().getParcelableExtra("recipe");
                int recipeId = recipe.getId();
                recipedatabase.recipeDao().deleteRecipeId(recipeId);
                Toast.makeText(this,"Recipe Deleted", Toast.LENGTH_SHORT).show();

            }else if(getIntent().hasExtra("database")){
                Recipe recipe = getIntent().getParcelableExtra("database");
                int recipeId = recipe.getId();
                recipedatabase.recipeDao().deleteRecipeId(recipeId);
                Toast.makeText(this,"Recipe Deleted", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e) {
            //The only error we can get is if the recipe already exist in the database
            //if it does we simply catch it an notify the user
            e.printStackTrace();
            //A toast to notify the user that the recipe already exist in their favorites
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

        }
    }
    public void insertRecipe() {
        //Doing a try/catch to catch any errors
        RandomRecipeModel randomRecipeModel = getIntent().getParcelableExtra("recipe");
        try {
            if (getIntent().hasExtra("recipe")) {

                // Creating int and Strings to place the extras that were sent from the
                //RecipeSearchResultsAdapter onClickListener
                int recipeID = randomRecipeModel.getId();
                String recipeTitle = randomRecipeModel.getTitle();
                String recipeImage = randomRecipeModel.getImage();
                int recipeServings = randomRecipeModel.getServings();
                int recipeReadyInMinutes = randomRecipeModel.getReadyInMinutes();
                String recipeDescriptions = randomRecipeModel.getSummary();


                //Creating an insert to place all these int and strings into the database
                Recipe recipe = new Recipe();
                recipe.setId(recipeID);
                recipe.setTitle(recipeTitle);
                recipe.setImage(recipeImage);
                recipe.setServings(recipeServings);
                recipe.setReadyInMinutes(recipeReadyInMinutes);
                recipe.setDescription(recipeDescriptions);
                //Calling the database and inserting the recipe
                recipedatabase.recipeDao().insert(recipe);
                //A toast to notify the user that the recipe was place into their favorites
                Toast.makeText(this, "Recipe added to favorites", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            //The only error we can get is if the recipe already exist in the database
            //if it does we simply catch it an notify the user
            e.printStackTrace();
            //A toast to notify the user that the recipe already exist in their favorites
            Toast toast = Toast.makeText(this,"Recipe already in favorites", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}