package com.techelevator.model;

import java.util.List;

public class RecipeDto {
    private String recipe_name;
    private String recipe_instructions;
    private List<IngredientDto> ingredientList;

    public RecipeDto(String recipe_name, String recipe_instructions, List<IngredientDto> ingredientList) {
        this.recipe_name = recipe_name;
        this.recipe_instructions = recipe_instructions;
        this.ingredientList = ingredientList;
    }
    public RecipeDto(){

    }
    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_instructions() {
        return recipe_instructions;
    }

    public void setRecipe_instructions(String recipe_instructions) {
        this.recipe_instructions = recipe_instructions;
    }

    public List<IngredientDto> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<IngredientDto> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
