package com.techelevator.model;

import java.util.List;

public class RecipeIngredient {
    private int recipeId;
    private List<IngredientDto> ingredients;
    private RecipeDto recipe;

    public RecipeIngredient(int recipeId, List<IngredientDto> ingredients, RecipeDto recipe) {
        this.recipeId = recipeId;
        this.ingredients = ingredients;
        this.recipe = recipe;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeDto getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDto recipe) {
        this.recipe = recipe;
    }
}
