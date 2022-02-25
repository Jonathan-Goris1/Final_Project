package com.zybooks.finalproject.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Creating a table in the database
@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {

    //Setting the primaryKey to id
    @PrimaryKey
    private int id;
    //Creating other columns row in the table
    private String title;
    private int servings;
    public String sourceUrl;
    private int readyInMinutes;
    private String image;
    private String description;

    //Creating the constructor
    public Recipe(int id, String title, int servings, int readyInMinutes, String image, String descriptions) {
        this.id = id;
        this.title = title;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.description = descriptions;
    }

    public Recipe() {

    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        title = in.readString();
        servings = in.readInt();
        readyInMinutes = in.readInt();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Creating Getters and Setters to be used to get or set a string or int
    //to the database

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(servings);
        parcel.writeInt(readyInMinutes);
        parcel.writeString(image);
        parcel.writeString(description);
    }
}
