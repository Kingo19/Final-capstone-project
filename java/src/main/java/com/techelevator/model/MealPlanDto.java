package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class MealPlanDto {
    private int planId;
    private String planName;
    private LocalDate planDate;
    private int userId;
    private List<MealDto> meals; // List of meals in the plan

    // Constructors, getters, and setters

    public MealPlanDto() {
    }

    public MealPlanDto(int planId, String planName, LocalDate planDate, int userId, List<MealDto> meals) {
        this.planId = planId;
        this.planName = planName;
        this.planDate = planDate;
        this.userId = userId;
        this.meals = meals;
    }

    // Standard getters and setters
}
