package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;

    private final UserDao userDao;

    public RecipeController(RecipeDao recipeDao, RecipeIngredientDao recipeIngredientDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.userDao = userDao;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "user/recipes/add", method = RequestMethod.POST)
    public void addRecipe(Principal principal, @Valid @RequestBody RecipeIngredientDto recipeIngredientDto) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<RecipeIngredientDto> oldRecipe = recipeIngredientDao.getRecipeAndIngredientsFromName(recipeIngredientDto.getRecipe().getRecipe_name());
        try {
            if(oldRecipe.get(0).getIngredients().size()>=1){
                throw new DaoException("Recipe already exists.");
            }
            recipeIngredientDao.addRecipeIngredientConnection(recipeIngredientDto);
            int recipeId = recipeDao.getRecipeID(recipeIngredientDto.getRecipe());
            recipeIngredientDao.addRecipeToUser(userId, recipeId);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeIngredientDto.toString()
            );
        }
    }


    @RequestMapping(path = "user/recipes/{recipeName}", method = RequestMethod.GET)
    public List<RecipeIngredientDto> fetchRecipeInfo(Principal principal, @Valid @PathVariable String recipeName){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        try {
            if(recipeDao.checkUserRecipe(userId, recipeName)){
                recipeIngredientDtoList = recipeIngredientDao.getRecipeAndIngredientsFromName(recipeName);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeName
            );
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return recipeIngredientDtoList;
    }

    @RequestMapping(path = "user/recipes", method = RequestMethod.GET)
    public List<RecipeIngredientDto> getAllUserRecipes(Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return recipeIngredientDao.getRecipesByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "user/recipes/{recipeName}/edit", method = RequestMethod.PUT)
    public void editRecipe(Principal principal, @Valid @RequestBody RecipeIngredientDto recipeToEdit, @Valid @PathVariable String recipeName){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        try {
            if(recipeDao.checkUserRecipe(userId, recipeName)){
                recipeIngredientDao.getRecipeAndIngredientsFromName(recipeName);
                recipeIngredientDao.updateRecipeAndIngredient(recipeToEdit, recipeName);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Argument"
            );
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(path = "user/recipes/names",method = RequestMethod.GET)
    public List<String> getUserRecipeNames(Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<String> names = recipeIngredientDao.getUserRecipeNames(userId);
        System.out.println(names);
        return names;
    }
}
