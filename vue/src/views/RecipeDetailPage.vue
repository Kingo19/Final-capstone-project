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
    data() {
      return {
        dummyRecipes: [
          {
            name: "Pasta Carbonara",
            instructions: "Lorem ipsum dolor sit amet...",
            ingredients: ["Pasta", "Eggs", "Bacon", "Parmesan"],
          },
          {
            name: "Chicken Stir Fry",
            instructions: "Consectetur adipiscing elit...",
            ingredients: ["Chicken", "Vegetables", "Soy Sauce", "Rice"],
          },
          {
            name: "Grilled Salmon",
            instructions: "Ut enim ad minim veniam...",
            ingredients: ["Salmon", "Lemon", "Olive Oil", "Herbs"],
          },
          {
            name: "Vegetarian Lasagna",
            instructions: "Duis aute irure dolor in reprehenderit...",
            ingredients: ["Lasagna Noodles", "Tomato Sauce", "Ricotta", "Spinach"],
          },
          {
            name: "Shrimp Scampi",
            instructions: "Excepteur sint occaecat cupidatat non proident...",
            ingredients: ["Shrimp", "Garlic", "White Wine", "Pasta"],
          },
          {
            name: "Mango Salsa Chicken",
            instructions: "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
            ingredients: ["Chicken Breast", "Mango", "Red Onion", "Cilantro"],
          },
          {
            name: "Caprese Salad",
            instructions: "Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat...",
            ingredients: ["Tomatoes", "Mozzarella", "Basil", "Balsamic Glaze"],
          },
          {
            name: "Chocolate Chip Cookies",
            instructions: "Nisi ut aliquid ex ea commodi consequatur?",
            ingredients: ["Flour", "Chocolate Chips", "Butter", "Sugar"],
          },
          {
            name: "Beef Tacos",
            instructions: "Culpa qui officia deserunt mollit anim id est laborum...",
            ingredients: ["Ground Beef", "Taco Shells", "Lettuce", "Cheese"],
          },
          {
            name: "Vegan Buddha Bowl",
            instructions: "Sunt in culpa qui officia deserunt mollit anim id est laborum...",
            ingredients: ["Quinoa", "Roasted Vegetables", "Avocado", "Tahini Dressing"],
          },
        
        ],
      };
    },
  };
  </script>
  