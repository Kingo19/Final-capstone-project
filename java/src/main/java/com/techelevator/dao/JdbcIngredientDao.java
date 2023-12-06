package com.techelevator.dao;
import com.techelevator.controller.MealController;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techelevator.model.IngredientDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcIngredientDao implements IngredientDao{
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private JdbcTemplate jdbcTemplate;
    public JdbcIngredientDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void getIdOrInsertIdReturningId(){

    }

    /**
     * Takes in ingredientDto
     * adds it to database
     * **SHOULD NOT BE CALLED IF OBJECT ALREADY IN DB**
     *
     * @param ingredientDto
     * @return in corresponding to the id of the new object
     */
    public int addIngredientReturnId(IngredientDto ingredientDto) {
        Ingredient newIngredient =  new Ingredient();
        newIngredient.setIngredient_name(ingredientDto.getIngredient_name());
        String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?) RETURNING ingredient_id;";
        try {
            int result = jdbcTemplate.queryForObject(sql, Integer.class, ingredientDto.getIngredient_name());
            newIngredient.setId(result);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            logger.error("Error adding ingredient: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + ingredientDto.toString()
            );
        }
        return newIngredient.getId();
    }

    /**
     * Takes in ingredientDto
     * adds object to db
     * **SHOULD NOT BE CALLED IF OBJECT ALREADY IN DB**
     * transfers Dto information to Object form
     *
     * @param ingredientDto
     * @return ingredient with the data of the Dto AND the new id from the db
     */
    public Ingredient addReturnIngredient(IngredientDto ingredientDto) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredient_name(ingredientDto.getIngredient_name());
        String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?) RETURNING ingredient_id;";
        try {
            int result = jdbcTemplate.queryForObject(sql, Integer.class, ingredientDto.getIngredient_name());
            newIngredient.setId(result);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            logger.error("Error adding ingredient: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + ingredientDto.toString()
            );
        }
        return newIngredient;
    }

    /**
     * Takes in IngredientDto,
     * returns id if found in db
     * returns 0 if not found in db
     *
     * @param ingredientDto
     * @return int corresponding to the ID of the ingredient, 0 if not found
     */
    public int getIngredientID (IngredientDto ingredientDto){
        int ID = 0;
        String sql = "SELECT ingredient_id FROM ingredient WHERE ingredient_name = (?);";
        try {
            ID = jdbcTemplate.queryForObject(sql, Integer.class, ingredientDto.getIngredient_name());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (EmptyResultDataAccessException e){
            return ID;
        }
        return ID;
    }

    /**
     * Takes in IngredientDto,
     * adds information to Ingredient object,
     * returns Ingredient
     *
     * @param ingredientDto
     * @return ingredient with name and ID set
     */
    public Ingredient getIngredient(IngredientDto ingredientDto){
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredient_name(ingredientDto.getIngredient_name());
        String sql = "SELECT ingredient_id FROM ingredient WHERE ingredient_name = (?);";
        try {
            int result = jdbcTemplate.queryForObject(sql, Integer.class, ingredientDto.getIngredient_name());
            newIngredient.setId(result);
            return newIngredient;
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (EmptyResultDataAccessException e){
            return addReturnIngredient(ingredientDto);
        }
    }

    /**
     * takes in an id
     * returns its corresponding ingredient object
     * if it doesn't exist, returns a null
     * @param id
     * @return
     */

    public IngredientDto getIngredientFromId(int id){
        IngredientDto ingredient = new IngredientDto();
        String sql = "SELECT ingredient_name FROM ingredient WHERE ingredient_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            ingredient = mapRowToIngredient(results);
        }
        if(ingredient.getIngredient_name() == null){
            throw new DaoException("Error receiving ingredient info.");
        }
        return ingredient;
    }

    private IngredientDto mapRowToIngredient(SqlRowSet results){
        IngredientDto ingredient = new IngredientDto();
        ingredient.setIngredient_name(results.getString("ingredient_name"));
        return ingredient;
    }
}
