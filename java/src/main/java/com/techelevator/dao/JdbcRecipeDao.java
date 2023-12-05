package com.techelevator.dao;

import com.techelevator.controller.MealController;
import com.techelevator.exception.DaoException;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcRecipeDao implements RecipeDao{

    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //TODO: implement userIds to know ownership

    /**
     * takes in recipeDto,
     * adds to the db
     * **SHOULD NOT BE CALLED IF OBJECT ALREADY IN DB**
     *
     * @param recipeDto
     * @return int corresponding to the new id from the db
     */
    public int addRecipeReturnId(RecipeDto recipeDto) {
        String sql = "INSERT INTO recipe(recipe_name, recipe_instructions) VALUES (?, ?) RETURNING recipe_id;";
        try {
            int result = jdbcTemplate.queryForObject(sql, Integer.class, recipeDto.getRecipe_name(), recipeDto.getRecipe_instructions());
            return result;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            logger.error("Error adding recipe: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeDto.toString()
            );
            return 0; // or throw an exception
        }
    }

    /**
     * takes in recipeDto
     *
     * @param recipeDto
     * @return int corresponding to the id of the object in the db
     */
    public int getRecipeID(RecipeDto recipeDto) {
        String sql = "SELECT recipe_id FROM recipe WHERE recipe_name = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, recipeDto.getRecipe_name());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (EmptyResultDataAccessException e) {
            return 0; // Indicates no result found
        } catch (Exception e) {
            logger.error("Error retrieving recipe ID: ", e);
            throw new DaoException("Error retrieving recipe ID", e);
        }
    }
}
