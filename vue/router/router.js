import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '../src/components/Home.vue';
import IngredientsView from '../src/components/IngredientsView.vue';
import RecipieView from '../src/components/RecipieView'
import addRecipe from "../src/components/addRecipe.vue";
import RecipeListPage from "@/views/RecipeListPage.vue";
import RecipeDetailPage from "@/views/RecipeDetailPage.vue";



const routes = [
  { path: '/', component: HomePage },
  { path: '/ingredients', component: IngredientsView },
  { path: '/recipes', component: RecipieView },
  { path: '/recipes/add', component: addRecipe },
  {
    path: "/all/recipes",
    component: RecipeListPage,
  },
  {
    path: "/all/recipe/:id",
    component: RecipeDetailPage,
  },


];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
