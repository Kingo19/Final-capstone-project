package com.techelevator.model;

import java.util.List;

public class Meal {
    private int mealId;
    private String mealName;
    private List<RecipeIngredientDto> recipeInfo;
    private String type;

    public Meal(int mealId, String mealName, List<RecipeIngredientDto> recipeInfo,String type) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.recipeInfo = recipeInfo;
    }

    public Meal() {

    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public List<RecipeIngredientDto> getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(List<RecipeIngredientDto> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
