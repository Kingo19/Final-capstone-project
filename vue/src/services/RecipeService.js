import axios from 'axios';


export default {
    addRecipeAndIngredients(recipeIngredient) {
        axios.post('http://localhost:9000/user/recipes/add', recipeIngredient)
    },

    getAllRecipes(user) {
        return axios.get('http://localhost:9000/user/recipes', user)
    },

    getRecipeByName(name){
        return axios.get(`http://localhost:9000/user/recipes/${name}`)
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
    },

    postMealPlan(mealPlan){
        return axios.post('http://localhost:9000/user/plans/add', mealPlan)
    },
/*    async addMeal(mealData) {
        await axios.post('user/meals/add', mealData);
    }*/
    getNextWeeksPlan(){
        return axios.get('http://localhost:9000/user/next-week')
    },

    getDayPlan(date){
        console.log("inner service")
        console.log(date)
        return axios.get(`http://localhost:9000/user/plans/${date}`)
    }


}
