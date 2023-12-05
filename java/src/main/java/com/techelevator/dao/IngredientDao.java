package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;

public interface IngredientDao {

//    Original
//    public int addIngredient(IngredientDto ingredientDto);
//    public int getIngredientID(IngredientDto ingredientDto);

    public int addIngredientReturnId(IngredientDto ingredientDto);
    public Ingredient addReturnIngredient(IngredientDto ingredientDto);
    public int getIngredientID (IngredientDto ingredientDto);
    public Ingredient getIngredient(IngredientDto ingredientDto);

    public IngredientDto getIngredientFromId(int id);
}
