import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/user/recipes/add', recipeIngredient)
    },

    getAllRecipes(user) {
        return axios.get('http://localhost:9000/user/recipes', user)
    },

    getRecipeByName(){
        return axios.get('http://localhost:9000/user/recipes/{recipeName}')
    },

    modifyRecipeAndIngredients(orgname, passin) {
        let recipeName = passin.recipe.recipe_name
        axios.put(  `http://localhost:9000/user/recipes/${orgname}/edit`, passin)
    },

    getUserRecipeNames(){
      let url = 'http://localhost:9000/user/recipes/names'
        return axios.get(url)
    },

    addMeal(objToSend){
        return axios.post('http://localhost:9000/user/meals/add', objToSend )
    },

    getUserMeals(){
        return axios.get('http://localhost:9000/user/meals' )
    }


}
