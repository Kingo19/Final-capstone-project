import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/recipes/add', recipeIngredient)
    },

    getAllRecipes(){
        let check =  axios.get('http://localhost:9000/recipes/all').then(cu =>
        console.log(cu)).catch(cu => console.log(cu + "trigger+"))
        console.log(Object.values(check[0]))
        return check
    }
}
