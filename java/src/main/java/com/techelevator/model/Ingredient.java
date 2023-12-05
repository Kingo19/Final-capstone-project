package com.techelevator.model;

public class Ingredient {
    private String ingredient_name;
    private int id = 0;
    public Ingredient(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }
    public String getIngredient_name() {
        return ingredient_name;
    }
    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_name='" + ingredient_name + '\'' +
                ", id=" + id +
                '}';
    }
}
