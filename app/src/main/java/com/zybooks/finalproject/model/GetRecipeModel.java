package com.zybooks.finalproject.model;

import java.util.ArrayList;

public class GetRecipeModel {

        private boolean veryHealthy;
        private boolean cheap;
        private boolean veryPopular;
        private boolean sustainable;
        private float spoonacularScore;
        private float healthScore;
        private String sourceName;
        private float pricePerServing;
        ArrayList < Object > extendedIngredients = new ArrayList < Object > ();
        private float id;
        private String title;
        private float readyInMinutes;
        private float servings;
        private String sourceUrl;
        private String image;
        private String imageType;
        Nutrition NutritionObject;
        private String summary;
        ArrayList < Object > cuisines = new ArrayList < Object > ();
        ArrayList < Object > dishTypes = new ArrayList < Object > ();
        ArrayList < Object > diets = new ArrayList < Object > ();
        ArrayList < Object > occasions = new ArrayList < Object > ();
        private String instructions;
        ArrayList < Object > analyzedInstructions = new ArrayList < Object > ();
        private String originalId = null;
        private String spoonacularSourceUrl;

        public boolean getVeryHealthy() {
            return veryHealthy;
        }

        public boolean getCheap() {
            return cheap;
        }

        public boolean getVeryPopular() {
            return veryPopular;
        }

        public boolean getSustainable() {
            return sustainable;
        }



        public float getSpoonacularScore() {
            return spoonacularScore;
        }

        public float getHealthScore() {
            return healthScore;
        }


        public String getSourceName() {
            return sourceName;
        }

        public float getPricePerServing() {
            return pricePerServing;
        }

        public float getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public float getReadyInMinutes() {
            return readyInMinutes;
        }

        public float getServings() {
            return servings;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public String getImage() {
            return image;
        }

        public String getImageType() {
            return imageType;
        }

        public Nutrition getNutrition() {
            return NutritionObject;
        }

        public String getSummary() {
            return summary;
        }



        public String getInstructions() {
            return instructions;
        }

        public String getOriginalId() {
            return originalId;
        }

        public String getSpoonacularSourceUrl() {
            return spoonacularSourceUrl;
        }

        // Setter Methods



        public void setVeryHealthy(boolean veryHealthy) {
            this.veryHealthy = veryHealthy;
        }

        public void setCheap(boolean cheap) {
            this.cheap = cheap;
        }

        public void setVeryPopular(boolean veryPopular) {
            this.veryPopular = veryPopular;
        }

        public void setSustainable(boolean sustainable) {
            this.sustainable = sustainable;
        }


        public void setSpoonacularScore(float spoonacularScore) {
            this.spoonacularScore = spoonacularScore;
        }

        public void setHealthScore(float healthScore) {
            this.healthScore = healthScore;
        }


        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public void setPricePerServing(float pricePerServing) {
            this.pricePerServing = pricePerServing;
        }

        public void setId(float id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setReadyInMinutes(float readyInMinutes) {
            this.readyInMinutes = readyInMinutes;
        }

        public void setServings(float servings) {
            this.servings = servings;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public void setNutrition(Nutrition nutritionObject) {
            this.NutritionObject = nutritionObject;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }



        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public void setOriginalId(String originalId) {
            this.originalId = originalId;
        }

        public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
            this.spoonacularSourceUrl = spoonacularSourceUrl;
        }


    public class Nutrition {
        ArrayList<Object> nutrients = new ArrayList<Object>();
        ArrayList<Object> properties = new ArrayList<Object>();
        ArrayList<Object> flavonoids = new ArrayList<Object>();
        ArrayList<Object> ingredients = new ArrayList<Object>();


    }
}




