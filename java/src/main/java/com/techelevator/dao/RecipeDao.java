package com.techelevator.dao;

import com.techelevator.model.RecipeDto;

import java.util.List;

public interface RecipeDao {
    int addRecipeReturnId(RecipeDto recipeDto);
    public int getRecipeID(RecipeDto recipeDto);
    public RecipeDto getRecipeFromId(int id);
    public boolean checkUserRecipe(int userId, String recipeName);
}
