import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/user/recipes/add', recipeIngredient)
    },

    async getAllRecipes(user) {
        return axios.get('http://localhost:9000/user/recipes', user)
    }
}
