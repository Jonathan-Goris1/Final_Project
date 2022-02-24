package com.zybooks.finalproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Creating a table in the database
@Entity(tableName = "recipe_table")
public class Recipe {

    //Setting the primaryKey to id
    @PrimaryKey
    private int id;
    //Creating other columns row in the table
    private String title;
    private int servings;
    private int readyInMinutes;
    private String image;
    private String steps;
    //Creating the constructor
    public Recipe(int id, String title, int servings, int readyInMinutes, String image, String steps) {
        this.id = id;
        this.title = title;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.steps = steps;
    }

    public Recipe() {

    }
    //Creating Getters and Setters to be used to get or set a string or int
    //to the database
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getServings() {
        return servings;
    }

    public int getReadyInMinutes() { return readyInMinutes; }

    public String getImage() {
        return image;
    }

    public String getSteps() {
        return steps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
