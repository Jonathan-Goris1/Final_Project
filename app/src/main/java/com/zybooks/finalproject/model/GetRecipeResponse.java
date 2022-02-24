package com.zybooks.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRecipeResponse {

    @SerializedName("root")
    @Expose
    private List<GetRecipeModel> retrieveRecipeModel;

    public List<GetRecipeModel> getRecipeID(){
        return retrieveRecipeModel;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageUrls")
    @Expose
    private List<String> imageUrls = null;
}
