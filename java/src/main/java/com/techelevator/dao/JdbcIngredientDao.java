package com.techelevator.dao;

import com.techelevator.model.IngredientDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class JdbcIngredientDao implements IngredientDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcIngredientDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int addIngredient(IngredientDto ingredientDto){
        int resultID = ingredientDto.getId();
        try{
            ingredientDto.setId(getIngredientID(ingredientDto));
        } catch (Exception e) {
            System.out.println("uh oh." + e);
        }
        if(resultID == 0){
            String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?) RETURNING ingredient_id;";
            jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name());
            ingredientDto.setId( jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name()));
        }
        return ingredientDto.getId();
    }

    public int getIngredientID(IngredientDto ingredientDto){
        int id = 0;
        String sql = "SELECT ingredient_id FROM ingredient WHERE ingredient_name = ?;";
        System.out.println();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, ingredientDto.getIngredient_name());
        while(result.next()){
            id = result.getInt("ingredient_id");
        }
        System.out.println(id);
        return id;
    }
}
