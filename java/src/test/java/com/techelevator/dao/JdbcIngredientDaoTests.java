package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class JdbcIngredientDaoTests extends BaseDaoTests{

    private final IngredientDto ingredient = new IngredientDto("Chips");

    private JdbcIngredientDao ingredientDao;

    @Before
    public void setup() {
        ingredientDao = new JdbcIngredientDao(dataSource);
    }

    @Test
    public void addReturnIngredient_returns_correct_Ingredient() {
        Ingredient testIngredient = new Ingredient("Chips");
        testIngredient.setId(1);
        Ingredient results = ingredientDao.addReturnIngredient(ingredient);
        assertIngredientsEqual(testIngredient, results);
    }

    @Test
    public void getIngredientID_returns_correct_Id() {
        int testId = ingredientDao.addIngredientReturnId(ingredient);
        int results = ingredientDao.getIngredientID(ingredient);
        Assert.assertEquals(testId, results);
    }

    @Test
    public void getIngredient_returns_correct_Ingredient() {
        Ingredient testIngredient = new Ingredient("Chips");
        testIngredient.setId(1);
        ingredientDao.addReturnIngredient(ingredient);
        Ingredient results = ingredientDao.getIngredient(ingredient);
        assertIngredientsEqual(testIngredient, results);
    }

    public void assertIngredientsEqual(Ingredient expected, Ingredient results) {
        Assert.assertEquals(expected.getIngredient_name(), results.getIngredient_name());
        Assert.assertEquals(expected.getId(), results.getId());
    }
}
