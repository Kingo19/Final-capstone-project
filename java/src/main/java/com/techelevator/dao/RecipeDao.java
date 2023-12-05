package com.techelevator.dao;

import com.techelevator.model.RecipeDto;

import java.util.List;

public interface RecipeDao {
//    public int addRecipe(RecipeDto recipeDto);
    int addRecipeReturnId(RecipeDto recipeDto);
    public int getRecipeID(RecipeDto recipeDto);
}
