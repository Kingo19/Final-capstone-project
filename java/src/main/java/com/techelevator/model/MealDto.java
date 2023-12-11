package com.techelevator.model;


import java.util.List;

public class MealDto {
    private String mealName;
    private List<String> recipeNames;
    private String type;

    public MealDto(String mealName, List<String> recipeNames,String type) {
        this.mealName = mealName;
        this.recipeNames = recipeNames;
        this.type = type;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(List<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}