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
    @RequestMapping(path = "recipes/add", method = RequestMethod.POST)
    public void addRecipe(Principal principal, @Valid @RequestBody RecipeIngredientDto recipeIngredientDto) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        try {
            recipeIngredientDao.addRecipeIngredientConnection(recipeIngredientDto);
            int recipeId = recipeDao.getRecipeID(recipeIngredientDto.getRecipe());
            recipeIngredientDao.addRecipetoUser(userId, recipeId);
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


    @RequestMapping(path = "recipes/{id}", method = RequestMethod.GET)
    public List<RecipeIngredientDto> fetchRecipeInfo(Principal principal, @Valid @PathVariable int id){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        try {
            if(recipeDao.checkUserRecipe(userId, id)){
                recipeIngredientDtoList = recipeIngredientDao.getRecipeAndIngredientsFromId(id);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + id
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
    @RequestMapping(path = "recipes/{id}/edit", method = RequestMethod.PUT)
    public void editRecipe(Principal principal, @Valid @RequestBody RecipeIngredientDto recipeToEdit, @Valid @PathVariable int id){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        int recipeId = id;
        try {
            if(recipeDao.checkUserRecipe(userId, recipeId)){
                recipeIngredientDao.updateRecipeAndIngredient(recipeToEdit, recipeId);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e
            );
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
