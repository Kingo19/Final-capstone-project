package com.techelevator.dao;

import com.techelevator.model.RecipeIngredientDto;

import java.util.List;

public interface MealDao {
    public void createMeal(List<RecipeIngredientDto> recipeIngredientList);
}
