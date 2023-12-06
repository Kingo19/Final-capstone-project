import axios from 'axios';



export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/recipes/add', recipeIngredient)
    }
}
