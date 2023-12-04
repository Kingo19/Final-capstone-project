package com.techelevator.dao;

import java.util.List;

public interface RecipeDao {
    public void addRecipe();
    public List<IngredientDao> listIngredients();
}
