package com.techelevator.dao;

import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcRecipeDao implements RecipeDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //TODO: implement userIds to know ownership
    @Override
    public int addRecipe(RecipeDto recipeDto) {
        int resultID = 0;
        try{
            resultID = getRecipeID(recipeDto);
        } catch (Exception e) {
            System.out.println("Recipe Problem. " + e);
        }
        if(resultID == 0){
            String sql = "INSERT INTO recipe(recipe_name, recipe_instructions) VALUES (?,?) RETURNING recipe_id;";
            resultID = jdbcTemplate.queryForObject(sql, int.class,recipeDto.getRecipe_name(), recipeDto.getRecipe_instructions());
        }
        return resultID;
    }

    public int getRecipeID(RecipeDto recipeDto){
        int id = 0;
        String sql = "SELECT recipe_id FROM recipe WHERE recipe_name = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeDto.getRecipe_name());
        while(result.next()) {
            id = result.getInt("recipe_id");
        }
        return id;
    }
}
