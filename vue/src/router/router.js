import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/views/HomeView.vue';
import IngredientsView from '@/views/IngredientsView.vue';
import RecipieView from '@/views/RecipieView.vue'
import addRecipe from "@/components/AddRecipe.vue";
import RecipeDetailView from "@/views/RecipeDetailView.vue";
import RecipeListView from "@/views/RecipeListView.vue";



const routes = [
  { path: '/', component: HomePage },
  { path: '/ingredients', component: IngredientsView },
  { path: '/recipes', component: RecipieView },
  { path: '/recipes/add', component: addRecipe },
  {
    path: "/all/recipes",
    component: RecipeListView,
  },
  {
    path: "/all/recipe",
    component: RecipeDetailView,
  },


];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
