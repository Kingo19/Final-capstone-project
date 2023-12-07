package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcRecipeIngredientTests extends BaseDaoTests {
    private JdbcRecipeDao jdbcRecipeDao;
    private JdbcIngredientDao jdbcIngredientDao;
    private JdbcRecipeIngredientDao jdbcRecipeIngredientDao;
    private final IngredientDto INGREDIENT_1 = new IngredientDto("Chips");
    private final IngredientDto INGREDIENT_2 = new IngredientDto("Cheese");
    private final IngredientDto INGREDIENT_3 = new IngredientDto("Salsa");
    private List<IngredientDto> INGREDIENT_LIST_1 = new ArrayList<IngredientDto>();
    private List<IngredientDto> INGREDIENT_LIST_2 = new ArrayList<IngredientDto>();
    private final RecipeDto RECIPE_1 = new RecipeDto("Chips and Cheese", "Put 'em in a bowl or somethin'");
    private final RecipeDto RECIPE_2 = new RecipeDto("Chips and Salsa", "Put 'em in a bowl or somethin'");
    private RecipeIngredientDto RECIPE_INGREDIENT_1;
    private RecipeIngredientDto RECIPE_INGREDIENT_2;


    @Before
    public void setup() {
        jdbcRecipeDao  = new JdbcRecipeDao(dataSource);
        jdbcIngredientDao = new JdbcIngredientDao(dataSource);
        jdbcRecipeIngredientDao = new JdbcRecipeIngredientDao(dataSource,jdbcIngredientDao,jdbcRecipeDao);
        INGREDIENT_LIST_1.add(INGREDIENT_1);
        INGREDIENT_LIST_1.add(INGREDIENT_2);
        INGREDIENT_LIST_2.add(INGREDIENT_1);
        INGREDIENT_LIST_2.add(INGREDIENT_3);
        RECIPE_INGREDIENT_1 = new RecipeIngredientDto(INGREDIENT_LIST_1,RECIPE_1);
        RECIPE_INGREDIENT_2 = new RecipeIngredientDto(INGREDIENT_LIST_2, RECIPE_2);
    }

    @Test
    public void getIngredientIds_returns_list_of_ids_when_fed_list_of_ingredientDtos(){
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        List<Integer> results = jdbcRecipeIngredientDao.getIngredientIds(INGREDIENT_LIST_1);
        Assert.assertNotNull(results);
        Assert.assertEquals(expected.get(0), results.get(0));
        Assert.assertEquals(expected.get(1), results.get(1));
    }

    @Test
    public void getRecipeAndIngredientsFromId_returns_populated_object(){
        jdbcRecipeIngredientDao.addRecipeIngredientConnection(RECIPE_INGREDIENT_1);

        int recipeID = jdbcRecipeDao.getRecipeID(RECIPE_1);
        List<RecipeIngredientDto> result = jdbcRecipeIngredientDao.getRecipeAndIngredientsFromId(recipeID);
        Assert.assertNotNull(result);
        assertRecipeIngredientEquals(RECIPE_INGREDIENT_1, result.get(0));
    }

    public void assertRecipeIngredientEquals(RecipeIngredientDto expected ,RecipeIngredientDto result){
        RecipeDto expectedRecipe = expected.getRecipe();
        List<IngredientDto> expectedIngredients = expected.getIngredients();
        RecipeDto resultRecipe = result.getRecipe();
        List<IngredientDto> resultIngredients = result.getIngredients();
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedRecipe.getRecipe_name(),resultRecipe.getRecipe_name());
        Assert.assertEquals(expectedRecipe.getRecipe_instructions(), resultRecipe.getRecipe_instructions());
        for (int i = 0; i < expectedIngredients.size(); i++) {
            Assert.assertEquals(expectedIngredients.get(i).toString(),resultIngredients.get(i).toString());
        }
    }
}

