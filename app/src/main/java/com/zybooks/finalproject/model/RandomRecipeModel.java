package com.zybooks.finalproject.model;

import java.util.ArrayList;

public class RandomRecipeModel {

    public class Us{
        public double amount;
        public String unitShort;
        public String unitLong;
    }

    public class Metric{
        public double amount;
        public String unitShort;
        public String unitLong;
    }

    public class Measures{
        public Us us;
        public Metric metric;
    }

    public class ExtendedIngredient{
        public int id;
        public String aisle;
        public String image;
        public String consistency;
        public String name;
        public String nameClean;
        public String original;
        public String originalName;
        public double amount;
        public String unit;
        public ArrayList<String> meta;
        public Measures measures;
    }

    public class Ingredient{
        public int id;
        public String name;
        public String localizedName;
        public String image;
    }

    public class Equipment{
        public int id;
        public String name;
        public String localizedName;
        public String image;
    }

    public class Length{
        public int number;
        public String unit;
    }

    public class Step{
        public int number;
        public String step;
        public ArrayList<Ingredient> ingredients;
        public ArrayList<Equipment> equipment;
        public Length length;
    }

    public class AnalyzedInstruction{
        public String name;
        public ArrayList<Step> steps;
    }

    public class Recipe{
        public boolean vegetarian;
        public boolean vegan;
        public boolean glutenFree;
        public boolean dairyFree;
        public boolean veryHealthy;
        public boolean cheap;
        public boolean veryPopular;
        public boolean sustainable;
        public int weightWatcherSmartPoints;
        public String gaps;
        public boolean lowFodmap;
        public int aggregateLikes;
        public double spoonacularScore;
        public double healthScore;
        public String creditsText;
        public String license;
        public String sourceName;
        public double pricePerServing;
        public ArrayList<ExtendedIngredient> extendedIngredients;
        public int id;
        public String title;
        public int readyInMinutes;
        public int servings;
        public String sourceUrl;
        public String image;
        public String imageType;
        public String summary;
        public ArrayList<Object> cuisines;
        public ArrayList<String> dishTypes;
        public ArrayList<String> diets;
        public ArrayList<Object> occasions;
        public String instructions;
        public ArrayList<AnalyzedInstruction> analyzedInstructions;
        public Object originalId;
        public String spoonacularSourceUrl;
    }

    public class Root{
        public ArrayList<Recipe> recipes;
    }


}
