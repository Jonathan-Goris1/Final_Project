package com.zybooks.finalproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.zybooks.finalproject.R;
import com.zybooks.finalproject.adapter.OnRecipeListener;
import com.zybooks.finalproject.adapter.RecipeRecyclerViewAdapter;
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

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


   }

}