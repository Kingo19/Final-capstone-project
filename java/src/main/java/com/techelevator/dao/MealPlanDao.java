package com.techelevator.dao;
import com.techelevator.model.MealPlanDto;

import java.util.List;

public interface MealPlanDao {
    int createMealPlan(MealPlanDto mealPlan);
    MealPlanDto getMealPlanById(int planId);
    List<MealPlanDto> getAllMealPlansByUserId(int userId);
    void updateMealPlan(MealPlanDto mealPlan);
    void deleteMealPlan(int planId);
}

