import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/recipes/add', recipeIngredient)
    },

    async getAllRecipes() {
        return axios.get('http://localhost:9000/user/recipes')
    }
}
