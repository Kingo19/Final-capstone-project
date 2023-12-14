import {createStore as _createStore} from 'vuex';
import axios from 'axios';
import recipeService from "@/services/RecipeService";

export function createStore(currentToken, currentUser) {
    return _createStore({
        state: {
            token: currentToken || '',
            user: currentUser || {},
            userRecipeNames: [],
            recipes: [],
            meals: [],
            selectedMeal: null,
            selectedRecipe: null,
            switchView: false
        },
        mutations: {
            SET_AUTH_TOKEN(state, token) {
                state.token = token;
                localStorage.setItem('token', token);
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            },
            SET_USER(state, user) {
                state.user = user;
                localStorage.setItem('user', JSON.stringify(user));
            },
            LOGOUT(state) {
                localStorage.removeItem('token');
                localStorage.removeItem('user');
                state.token = '';
                state.user = {};
                axios.defaults.headers.common = {};
            },
            SETRECIPEOBJ(state, array) {
                state.recipeObjs = array
            },

            SETRECIPES(state, newRecipes) {
                state.recipes = newRecipes;
            },

            SETSELECTEDRECIPE(state, recipe) {
                state.selectedRecipe = recipe;
            },

            SETSELECTEDMEAL(state, recipe) {
                state.selectedRecipe = recipe;
            },
            SWITCHINGTHEVIEW(state) {
                state.switchView = !state.switchView
            }
        },


        getters: {
            isAuthenticated: state => {
                return !!state.token && Object.keys(state.user).length > 0;
            },

            getRecipeByName: (state) => (recipeName) => {
                return state.recipes.find(recipe => recipe.recipe.recipe_name === recipeName);
            },


            getSwitch: (state) => () => {
                return state.switchView
            },

            getMealByName: (state) => (mealName) => {
                return state.meals.find(meal => meal.mealName === mealName);
            },

            getAllIngredients: state => {
                return state.recipes.reduce((acc, recipe) => {
                    return [...acc, ...recipe.ingredients];
                }, []);
            },

            setMeals(state, meals) {
                state.meals = meals;
            },

            getRecipes(state) {
                return state.recipes
            }
        },
        actions: {
            fetchAllRecipes({commit}) {
                commit('setRecipes', recipeService.getAllRecipes(state.user))
                /*                axios.get('/path/to/user/recipes', this.user)
                                    .then(response => {
                                        commit('setRecipes', response.data);
                                    })
                                    .catch(error => {
                                        console.error('Error fetching recipes:', error);
                                    });*/
            },

            fetchMealsByType({commit}, type) {
                axios.get(`user/meals/type/${type}`)
                    .then(response => {
                        commit('setMeals', response.data);
                    })
                    .catch(error => {
                        console.error('Error fetching meals by type:', error);
                    });
            },

            fetchAllUserMeals({commit}) {
                axios.get('user/meals')
                    .then(response => {
                        commit('setMeals', response.data);
                    })
                    .catch(error => {
                        console.error('Error fetching all user meals:', error);
                    });
            },

            MealDetails(mealData) {
                // Logic to process meal data
            },

            async getMealById(mealId) {
                const response = await axios.get(`user/meals/${mealId}`);
                return response.data;
            }


        },
    });
}
