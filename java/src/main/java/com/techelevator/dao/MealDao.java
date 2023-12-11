package com.techelevator.dao;

import com.techelevator.model.MealDto;
import com.techelevator.model.RecipeIngredientDto;

import java.util.List;

public interface MealDao {
//    public void createMeal(List<RecipeIngredientDto> recipeIngredientList);
    int createMeal(MealDto meal);
}
