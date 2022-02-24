package com.zybooks.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Database;
import androidx.room.Entity;

import java.util.ArrayList;
@Entity
public class RandomRecipeModel implements Parcelable {

        public int id;
        public String title;
        public int readyInMinutes;
        public int servings;
        public String sourceUrl;
        public String image;
        public String imageType;
        public String summary;

    public RandomRecipeModel(int id, String title, int readyInMinutes, int servings, String sourceUrl, String image, String imageType, String summary) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.image = image;
        this.imageType = imageType;
        this.summary = summary;
    }

    protected RandomRecipeModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        readyInMinutes = in.readInt();
        servings = in.readInt();
        sourceUrl = in.readString();
        image = in.readString();
        imageType = in.readString();
        summary = in.readString();
    }

    public static final Creator<RandomRecipeModel> CREATOR = new Creator<RandomRecipeModel>() {
        @Override
        public RandomRecipeModel createFromParcel(Parcel in) {
            return new RandomRecipeModel(in);
        }

        @Override
        public RandomRecipeModel[] newArray(int size) {
            return new RandomRecipeModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(readyInMinutes);
        parcel.writeInt(servings);
        parcel.writeString(sourceUrl);
        parcel.writeString(image);
        parcel.writeString(imageType);
        parcel.writeString(summary);
    }
}
