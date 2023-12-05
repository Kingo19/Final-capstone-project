package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeIngredientDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeIngredientDao implements RecipeIngredientDao{
    private JdbcTemplate jdbcTemplate;

    private JdbcIngredientDao jdbcIngredientDao;

    private JdbcRecipeDao jdbcRecipeDao;

    public JdbcRecipeIngredientDao(DataSource dataSource, JdbcIngredientDao jdbcIngredientDao, JdbcRecipeDao jdbcRecipeDao) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcIngredientDao = jdbcIngredientDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
    }


    /**
     * INCOMPLETE
     * takes in list of ingredientDtos
     * checks if item is already in db, if not, adds it and adds new id to the list
     * if is in db already SHOULD add id to the list
     *
     * @param ingredientDtoList
     * @return list of Integers corresponding to ids of ingredients in the db
     */
    public List<Integer> getIngredientIds(List<IngredientDto> ingredientDtoList) {
        //Gets back all ids except ones already in the system
        List<Integer> idsList = new ArrayList<>();
        for(IngredientDto currentIngredient : ingredientDtoList ){
            int checkForId = jdbcIngredientDao.getIngredientID(currentIngredient);
            if(checkForId == 0) {
                idsList.add(jdbcIngredientDao.addIngredientReturnId(currentIngredient));
            }
        }
        return idsList;
    }

    /**
     * takes in list of ingredientDtos,
     * turns list of Dtos into list of Ingredient objects *with ids*
     * **SHOULD NOT BE CALLED IF OBJECT IS NOT IN DB**
     *
     * @param ingredientDtoList
     * @return
     */
    public List<Ingredient> getListIngredients(List<IngredientDto> ingredientDtoList) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for(IngredientDto currentIngredient : ingredientDtoList ){
            ingredientList.add(jdbcIngredientDao.getIngredient(currentIngredient));
        }
        return ingredientList;
    }
}
