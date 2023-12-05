package com.techelevator.model;

import java.util.List;

public class RecipeIngredientDto {
    private List<IngredientDto> ingredients;
    private RecipeDto recipe;

    public RecipeIngredientDto(List<IngredientDto> ingredients, RecipeDto recipe) {
        this.recipe = recipe;
        this.ingredients = ingredients;
    }

    public RecipeIngredientDto(){

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

    @Override
    public String toString() {
        return "RecipeIngredientDto{" +
                "ingredients=" + ingredients +
                ", recipe=" + recipe +
                '}';
    }
}
