import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/user/recipes/add', recipeIngredient)
    },

    async getAllRecipes(user) {
        return axios.get('http://localhost:9000/user/recipes', user)
    },

    modifyRecipeAndIngredients(orgname, passin) {
        console.log(passin.recipe.recipe_name)
        let recipeName = passin.recipe.recipe_name
        axios.put(  `http://localhost:9000/user/recipes/${orgname}/edit`, passin)
    },

    getUserRecipeNames(){
      let url = 'http://localhost:9000/user/recipes/names'
        return axios.get(url)
    }
}
