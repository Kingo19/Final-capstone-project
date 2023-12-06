package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;

import java.util.List;

public interface RecipeIngredientDao {
    //public void addRecipeAndIngredients(RecipeIngredientDto recipeIngredientDto);
    public List<Integer> getIngredientIds(List<IngredientDto> ingredientDtoList);

    public void addRecipeIngredientConnection(RecipeIngredientDto rID);

    public RecipeIngredientDto getRecipeAndIngredientsFromId(int id);
    public List<RecipeIngredientDto> getAllRecipes();
}
