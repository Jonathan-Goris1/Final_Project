package com.zybooks.finalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchRecipeResponse {
    //@SerializedName allows you to create your own string with a different name.
    // the serialized name is used when connecting the HTML
    //Take a look at the response in a internet browser to see what
    //object you want to be extracted
    @SerializedName("results")
    @Expose
    private List<RandomRecipeModel> searchRecipeModel;

    public List<RandomRecipeModel> getSearchRecipe(){
        return searchRecipeModel;
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
    // Constructor for the request
    public SearchRecipeResponse(Integer id, String title, Integer readyInMinutes, Integer servings, String image) {
        this.id = id;
        this.title = title;
        this.image = image;

    }
    //Methods that return the requested response
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

}


