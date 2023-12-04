package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.model.IngredientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class MealController {
    private IngredientDao ingredientDao;

    public MealController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void addIngredient(@RequestBody IngredientDto ingredientDto) {
        try {
            ingredientDao.addIngredient(ingredientDto);
        } catch (Exception e){
            System.out.println("Didn't work. :/");
        }
    }
}
