package com.techelevator.model;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String recipeInstructions;

    public Recipe(int recipeId, String recipeName, String recipeInstructions) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }
}
