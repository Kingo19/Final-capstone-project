package com.techelevator.model;

import java.util.List;

public class DailyPlanDto {
    private String planName;
    private String date;
    private List<MealTimeDto> mealsWithTime;

    //    private List<String> mealIds;

//    public DailyPlanDto(String planName, String date, List<String> mealIds) {
//        this.planName = planName;
//        this.date = date;
//        this.mealIds = mealIds;
//    }

    public DailyPlanDto(String planName, String date, List<MealTimeDto> mealsWithTime) {
        this.planName = planName;
        this.date = date;
        this.mealsWithTime = mealsWithTime;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

//    public List<String> getMealIds() {
//        return mealIds;
//    }
//
//    public void setMealIds(List<String> mealIds) {
//        this.mealIds = mealIds;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MealTimeDto> getMealsWithTime() {
        return mealsWithTime;
    }

    public void setMealsWithTime(List<MealTimeDto> mealsWithTime) {
        this.mealsWithTime = mealsWithTime;
    }
}
