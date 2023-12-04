package com.techelevator.dao;

import com.techelevator.model.IngredientDto;

public interface IngredientDao {
    public int addIngredient(IngredientDto ingredientDto);
    public int getIngredientID(IngredientDto ingredientDto);
}
