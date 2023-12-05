import axios from 'axios';

export default {


    addRecipeAndIngredients(recipeIngredient) {
        return axios.post('recipes/add', recipeIngredient)
    }
}