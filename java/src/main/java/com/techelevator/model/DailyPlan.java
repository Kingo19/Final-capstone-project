package com.techelevator.model;

import java.util.List;

public class DailyPlan {
    private int planId;
    private int weekdayId;
    private String planName;
    private List<Meal> planMeals;

    public DailyPlan(int planId, int weekdayId, String planName, List<Meal> planMeals) {
        this.planId = planId;
        this.weekdayId = weekdayId;
        this.planName = planName;
        this.planMeals = planMeals;
    }

    public DailyPlan() {
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getWeekdayId() {
        return weekdayId;
    }

    public void setWeekdayId(int weekdayId) {
        this.weekdayId = weekdayId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<Meal> getPlanMeals() {
        return planMeals;
    }

    public void setPlanMeals(List<Meal> planMeals) {
        this.planMeals = planMeals;
    }
}
