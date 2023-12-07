import {createWebHistory} from 'vue-router';

import HomePage
    from '../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/HomeView.vue';
import IngredientsView
    from '../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/IngredientsView.vue';
import RecipieView
    from '../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/RecipieView.vue'
import addRecipe from "../components/AddRecipe.vue";
import RecipeDetailView
    from "../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/RecipeDetailView.vue";
import RecipeListView
    from "../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/RecipeListView.vue";
import LoginView
    from "../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/LoginView.vue";
import RegisterView
    from "../../../../../source/repos/steven-rebuck-student-code/team-november-final-capstone/vue/src/views/RegisterView.vue"


const routes = [
    {path: '/', component: HomePage},
    {path: '/ingredients', component: IngredientsView},
    {path: '/recipes', component: RecipieView},
    {path: '/recipes/add', component: addRecipe},
    {
        path: "/all/recipes",
        component: RecipeListView,
    },
    {
        path: "/all/recipe",
        component: RecipeDetailView,
    },
    {path: '/login', name: 'login', component: LoginView},
    {path: '/register', name: 'register', component: RegisterView}


];

export function createRouter() {
    return _createRouter({
        history: createWebHistory(),
        routes: routes
    })
}

export default router;
