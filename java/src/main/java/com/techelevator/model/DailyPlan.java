package com.techelevator.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public class DailyPlan {
    private int planId;
    private String planName;
    private List<MealTime> mealTimes;
    private int userId;
    private LocalDate dateOfPlan;



    public DailyPlan(int planId, String planName, List<MealTime> mealTimes, int userId, LocalDate dateOfPlan) {
        this.planId = planId;
        this.planName = planName;
        this.mealTimes = mealTimes;
        this.userId = userId;
        this.dateOfPlan = dateOfPlan;
    }

    public DailyPlan() {
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDateOfPlan() {
        return dateOfPlan;
    }

    public void setDateOfPlan(LocalDate dateOfPlan) {
        this.dateOfPlan = dateOfPlan;
    }

    public List<MealTime> getMealTimes() {
        return mealTimes;
    }

    public void setMealTimes(List<MealTime> mealTimes) {
        this.mealTimes = mealTimes;
    }
}
