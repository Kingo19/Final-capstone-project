package com.techelevator.model;

import java.util.List;

public class Meal {
    private int userId;
    private int mealId;
    private String mealName;
    private List<RecipeIngredient> recipeInfo;

    public Meal(int userId, int mealId, String mealName, List<String> recipeNames) {
        this.userId = userId;
        this.mealId = mealId;
        this.mealName = mealName;
        this.recipeInfo = recipeInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<RecipeIngredient> getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(List<RecipeIngredient> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}
