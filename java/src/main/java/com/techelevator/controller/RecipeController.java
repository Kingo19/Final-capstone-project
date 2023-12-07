package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "recipes/modify", method = RequestMethod.POST)
    public void modifyRecipe(@RequestBody RecipeDto recipeDto) {
        try {
            System.out.println(recipeDto);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeDto.toString()
            );
        }
    }

    @RequestMapping(path = "recipes/{id}", method = RequestMethod.GET)
    public List<RecipeIngredientDto> fetchRecipeInfo(@Valid @PathVariable int id){
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        try {
            recipeIngredientDtoList = recipeIngredientDao.getRecipeAndIngredientsFromId(id);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + id
            );
        }
        return recipeIngredientDtoList;
    }

    //temp for demo
    @RequestMapping(path = "recipes/all", method = RequestMethod.GET)
    public List<RecipeIngredientDto> fetchAllRecipeInfo(){
        System.out.println("Triggered");
        List<RecipeIngredientDto> recipeIngredientList = new ArrayList<>();
        try {
             recipeIngredientList = recipeIngredientDao.getAllRecipes();
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
        }
        int counter = 0;
        System.out.println(recipeIngredientList.get(0));
        System.out.println(counter);
        counter++;
        return recipeIngredientList;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "user/recipes/save", method = RequestMethod.POST)
//    public void saveRecipe(Principal principal, @Valid @RequestBody RecipeDto recipeToSave){
//        int userId = userDao.getUserByUsername(principal.getName()).getId();
//        int recipeId = recipeDao.getRecipeID(recipeToSave);
//        recipeIngredientDao.addRecipetoUser(userId, recipeId);
//    }

    @RequestMapping(path = "user/recipes", method = RequestMethod.GET)
    public List<RecipeIngredientDto> getAllUserRecipes(Principal principal){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return recipeIngredientDao.getRecipesByUserId(userId);
    }

//    @RequestMapping(path = "user", method = RequestMethod.GET)
//    public List<String> getUserInfo(Principal principal){
//        List<String> stringList = new ArrayList<>();
//        stringList.add(principal.getName());
//        int userId = userDao.getUserByUsername(principal.getName()).getId();
//        stringList.add(userId +"");
//        return stringList;
//    }

}
