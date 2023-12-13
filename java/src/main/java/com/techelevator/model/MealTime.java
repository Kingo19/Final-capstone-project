package com.techelevator.model;

import java.sql.Time;
import java.util.List;

public class MealTime {
    private int mealId;
    private String mealName;
    private Time mealTime;
    private List<RecipeIngredientDto> recipeInfo;
    private String type;

    public MealTime(int mealId, String mealName, Time mealTime, List<RecipeIngredientDto> recipeInfo, String type) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealTime = mealTime;
        this.recipeInfo = recipeInfo;
        this.type = type;
    }

    public MealTime() {

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

    public Time getMealTime() {
        return mealTime;
    }

    public void setMealTime(Time mealTime) {
        this.mealTime = mealTime;
    }
}
