package com.techelevator.dao;
import com.techelevator.controller.MealController;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
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
// Original Reference Code
    //All code works.
//    public int addIngredient(IngredientDto ingredientDto){
//        int resultID = ingredientDto.getId();
//        try{
//            ingredientDto.setId(getIngredientID(ingredientDto));
//        } catch (Exception e) {
//            logger.error("Error adding ingredient: ", e);
//            System.out.printf("%s%n%s%n%s%n%s%n",
//                    "Class: " + this.getClass(),
//                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
//                    "Exception: " + e,
//                    "Parameters: " + ingredientDto.toString()
//            );
//        }
//        if(resultID == 0){
//            String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?) RETURNING ingredient_id;";
//            jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name());
//            ingredientDto.setId( jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name()));
//        }
//        return ingredientDto.getId();
//    }
//
//    public int getIngredientID(IngredientDto ingredientDto){
//        int id = 0;
//        String sql = "SELECT ingredient_id FROM ingredient WHERE ingredient_name = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, ingredientDto.getIngredient_name());
//        while(result.next()){
//            id = result.getInt("ingredient_id");
//        }
//        return id;
//    }


    public int addIngredientReturnId(IngredientDto ingredientDto) {
        Ingredient newIngredient =  new Ingredient(ingredientDto.getIngredient_name());
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

    public Ingredient addReturnIngredient(IngredientDto ingredientDto) {
        Ingredient newIngredient = new Ingredient(ingredientDto.getIngredient_name());
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

    public Ingredient getIngredient(IngredientDto ingredientDto){
        Ingredient newIngredient = new Ingredient(ingredientDto.getIngredient_name());
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
}
