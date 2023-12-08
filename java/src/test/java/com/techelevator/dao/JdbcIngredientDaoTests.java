package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class JdbcIngredientDaoTests extends BaseDaoTests{

    private final IngredientDto INGREDIENT_DTO = new IngredientDto("Chips");

    private JdbcIngredientDao ingredientDao;

    @Before
    public void setup() {
        ingredientDao = new JdbcIngredientDao(dataSource);
    }

    @Test
    public void addReturnIngredient_returns_correct_Ingredient() {
        Ingredient testIngredient = new Ingredient();
        testIngredient.setIngredient_name("Chips");
        testIngredient.setId(1);
        Ingredient results = ingredientDao.addReturnIngredient(INGREDIENT_DTO);
        //this currently asserts false due to the testdb not resetting
        assertIngredientsEqual(testIngredient, results);
    }

    @Test
    public void getIngredientID_returns_correct_Id() {
        int testId = ingredientDao.addIngredientReturnId(INGREDIENT_DTO);
        int results = ingredientDao.getIngredientID(INGREDIENT_DTO);
        Assert.assertEquals(testId, results);
    }

    @Test
    public void getIngredient_returns_correct_Ingredient() {
        Ingredient testIngredient = ingredientDao.addReturnIngredient(INGREDIENT_DTO);
        Ingredient results = ingredientDao.getIngredient(INGREDIENT_DTO);
        assertIngredientsEqual(testIngredient, results);
    }

    public void assertIngredientsEqual(Ingredient expected, Ingredient results) {
        Assert.assertEquals(expected.getIngredient_name(), results.getIngredient_name());
        Assert.assertEquals(expected.getId(), results.getId());
    }
}
