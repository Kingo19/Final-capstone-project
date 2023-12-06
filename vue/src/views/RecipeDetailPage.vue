<template>
    <div>
      <RecipeDetailView :recipes="dummyRecipes" />
    </div>
  </template>
  
  <script>
  import RecipeDetailView from "@/components/RecipeDetailView.vue";
  
  export default {
    components: {
      RecipeDetailView,
    },
  };
  </script>
  