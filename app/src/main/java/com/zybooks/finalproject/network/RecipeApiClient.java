package com.zybooks.finalproject.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zybooks.finalproject.AppExecutors;
import com.zybooks.finalproject.model.RandomRecipeModel;
import com.zybooks.finalproject.model.RandomRecipeResponse;
import com.zybooks.finalproject.model.SearchRecipeModel;
import com.zybooks.finalproject.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RecipeApiClient {

    private MutableLiveData<List<RandomRecipeModel>> mRecipe;
    private static RecipeApiClient instance;

    // making global Runnable
    private RetrieveRecipeRunnable retrieveRecipeRunnable;

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    private RecipeApiClient() {

        mRecipe = new MutableLiveData<>();
    }

    public LiveData<List<RandomRecipeModel>> getRecipe() {
        return mRecipe;
    }


    public void searchRecipeApi() {
        if(retrieveRecipeRunnable != null){
            retrieveRecipeRunnable = null;
        }

        retrieveRecipeRunnable = new RetrieveRecipeRunnable();

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveRecipeRunnable);


        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Canceling the retrofit call
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MICROSECONDS);
    }


    //Retreiving data from RestApi by runnable class
    private class RetrieveRecipeRunnable implements Runnable {


        boolean cancelRequest;

        public RetrieveRecipeRunnable() {
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response objects

            try{
                Response response = getRecipe().execute();
                if (cancelRequest) {
                    return;
                }
                List<RandomRecipeModel> list = new ArrayList<>(((RandomRecipeResponse)response.body()).getRandomRecipe());
                if(response.code() == 200){
                    if(response.body() != null){
                        mRecipe.postValue(list);

                    }else{
                        List<RandomRecipeModel> currentRecipe = mRecipe.getValue();
                        currentRecipe.addAll(list);
                        mRecipe.postValue(currentRecipe);

                    }


                }else{
                    String error = response.errorBody().string();
                    Log.d("tag", "Error: " + error);
                    mRecipe.postValue(null);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                mRecipe.postValue(null);
            }


        }
        //Search Method/Query
        private Call<RandomRecipeResponse> getRecipe(){
            return RetrofitService.getRecipeApi().getRandomRecipe(Credentials.NUMBER,Credentials.Api_Key);

        }

        private void cancelRequest(){
            Log.d("Tag", "cancelRequest: " );
            cancelRequest = true;
        }
    }
}
