package com.techelevator.model;

import org.springframework.stereotype.Component;

import java.util.List;

public class RecipeDto {
    private String recipe_name;
    private String recipe_instructions;

    public RecipeDto(String recipe_name, String recipe_instructions) {
        this.recipe_name = recipe_name;
        this.recipe_instructions = recipe_instructions;
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

    @Override
    public String toString() {
        return "RecipeDto{" +
                "recipe_name='" + recipe_name + '\'' +
                ", recipe_instructions='" + recipe_instructions + '\'' +
                '}';
    }
}
