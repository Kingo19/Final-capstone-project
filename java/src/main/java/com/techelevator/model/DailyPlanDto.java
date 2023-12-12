package com.techelevator.model;

import java.util.List;

public class DailyPlanDto {
    private String planName;
    private String date;
    //private String weekday;
    private List<String> mealIds;

//    public DailyPlanDto(String planName, String date, List<String> mealIds) {
//        this.planName = planName;
//        this.date = weekday;
//        this.mealIds = mealIds;
//    }


    public DailyPlanDto(String planName, String date, List<String> mealIds) {
        this.planName = planName;
        this.date = date;
        this.mealIds = mealIds;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

//    public String getWeekday() {
//        return weekday;
//    }
//
//    public void setWeekday(String weekday) {
//        this.weekday = weekday;
//    }

    public List<String> getMealIds() {
        return mealIds;
    }

    public void setMealIds(List<String> mealIds) {
        this.mealIds = mealIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
