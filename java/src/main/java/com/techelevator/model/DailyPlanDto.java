package com.techelevator.model;

import java.util.List;

public class DailyPlanDto {
    private String planName;
    private String weekday;
    private List<String> mealIds;

    public DailyPlanDto(String planName, String weekday, List<String> mealIds) {
        this.planName = planName;
        this.weekday = weekday;
        this.mealIds = mealIds;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public List<String> getMealIds() {
        return mealIds;
    }

    public void setMealIds(List<String> mealIds) {
        this.mealIds = mealIds;
    }
}
