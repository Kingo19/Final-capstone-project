package com.techelevator.dao;

import com.techelevator.model.RecipeDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class JdbcRecipeDaoTests extends BaseDaoTests{

    private final RecipeDto testRecipe = new RecipeDto("Chips n Dip", "Place Chips in a serving" +
            "bowl with dip");
    private JdbcRecipeDao recipeDao;
    @Before
    public void setup() {
        recipeDao = new JdbcRecipeDao(dataSource);
    }

    @Test
    public void getRecipeID_returns_same_Id_as_addRecipeReturnId(){
        int addId = recipeDao.addRecipeReturnId(testRecipe);
        int getId = recipeDao.getRecipeID(testRecipe);
        Assert.assertEquals(addId,getId);
    }
}
