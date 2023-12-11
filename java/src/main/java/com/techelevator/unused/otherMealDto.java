package com.techelevator.unused;

import java.util.List;

public class otherMealDto {
    private int userId;
    private String mealName;
    private List<String> recipeNames;
    public otherMealDto(int userId, String mealName) {
        this.userId = userId;
        this.mealName = mealName;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
