package com.techelevator.model;

public class MealTimeDto {
    private int mealId;
    private String timeInString;
    public MealTimeDto(int mealId, String timeInString) {
        this.mealId = mealId;
        this.timeInString = timeInString;
    }
    public MealTimeDto() {
    }
    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getTimeInString() {
        return timeInString;
    }

    public void setTimeInString(String timeInString) {
        this.timeInString = timeInString;
    }
}
