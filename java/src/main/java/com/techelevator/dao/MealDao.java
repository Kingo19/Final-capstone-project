package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.MealDto;
import com.techelevator.model.RecipeIngredientDto;

import java.util.List;

public interface MealDao {
//    public void createMeal(List<RecipeIngredientDto> recipeIngredientList);
    public Meal createMeal(MealDto mealDto, int userId);
    public Meal mealDtoToMeal(MealDto mealDto);
    public Meal getMealByMealID(int mealId, int userId);
    public Meal createMealSVersion(MealDto mealDto, int userId);
    public List<Meal> getAllUserMeals(int userId);
}
