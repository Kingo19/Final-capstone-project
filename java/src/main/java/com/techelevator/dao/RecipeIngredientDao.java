package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;

import java.util.List;

public interface RecipeIngredientDao {
    //public void addRecipeAndIngredients(RecipeIngredientDto recipeIngredientDto);
    public List<Integer> getIngredientIds(List<IngredientDto> ingredientDtoList);
    public void addRecipeIngredientConnection(RecipeIngredientDto recipeIngredientDto);
    public List<RecipeIngredientDto> getRecipeAndIngredientsFromId(int recipeId);
    public List<RecipeIngredientDto> getRecipeAndIngredientsFromName(String recipeName);
    public void addRecipeToUser(int userId, int recipeId);
    public List<RecipeIngredientDto> getRecipesByUserId(int userId);
    public String updateRecipeAndIngredient(RecipeIngredientDto recipeIngredientDto, String recipeName);
    public List<String> getUserRecipeNames(int userId);
}
