import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/views/HomeView.vue';
import IngredientsView from '@/views/IngredientsView.vue';
import RecipieView from '@/views/RecipieView.vue'
import addRecipe from "@/components/AddRecipe.vue";
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
