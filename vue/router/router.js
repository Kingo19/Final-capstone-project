import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '../src/components/Home.vue';
import IngredientsView from '../src/components/IngredientsView.vue';
import RecipieView from '../src/components/RecipieView'
import addRecipe from "@/StevenFile/addRecipe.vue";



const routes = [
  { path: '/', component: HomePage },
  { path: '/ingredients', component: IngredientsView },
  { path: '/recipes', component: RecipieView },
  { path: '/input', component: addRecipe },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
