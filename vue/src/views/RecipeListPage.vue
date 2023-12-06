<template>
    <div>
      <RecipeListView :recipes="dummyRecipes" />
    </div>
  </template>
  
  <script>
  import RecipeListView from "@/components/RecipeListView.vue";
  
  export default {
    components: {
      RecipeListView,
    },
    data() {
      const imageUrl = "https://www.sandiegoprosthodontics.com/files/2018/09/Recipes-Banner.jpg";
  
      return {
        dummyRecipes: [
          {
            name: "Pasta Carbonara",
            instructions: "Lorem ipsum dolor sit amet...",
            ingredients: ["Pasta", "Eggs", "Bacon", "Parmesan"],
            author: "Chef John",
            image: imageUrl,
          },
          {
            name: "Chicken Stir Fry",
            instructions: "Consectetur adipiscing elit...",
            ingredients: ["Chicken", "Vegetables", "Soy Sauce", "Rice"],
            author: "Chef Jane",
            image: imageUrl,
          },
          {
            name: "Grilled Salmon",
            instructions: "Ut enim ad minim veniam...",
            ingredients: ["Salmon", "Lemon", "Olive Oil", "Herbs"],
            author: "Chef Alex",
            image: imageUrl,
          },
          {
            name: "Vegetarian Lasagna",
            instructions: "Duis aute irure dolor in reprehenderit...",
            ingredients: ["Lasagna Noodles", "Tomato Sauce", "Ricotta", "Spinach"],
            author: "Chef Emily",
            image: imageUrl,
          },
          {
            name: "Shrimp Scampi",
            instructions: "Excepteur sint occaecat cupidatat non proident...",
            ingredients: ["Shrimp", "Garlic", "White Wine", "Pasta"],
            author: "Chef Michael",
            image: imageUrl,
          },
          {
            name: "Mango Salsa Chicken",
            instructions: "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
            ingredients: ["Chicken Breast", "Mango", "Red Onion", "Cilantro"],
            author: "Chef Sarah",
            image: imageUrl,
          },
          {
            name: "Caprese Salad",
            instructions: "Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat...",
            ingredients: ["Tomatoes", "Mozzarella", "Basil", "Balsamic Glaze"],
            author: "Chef David",
            image: imageUrl,
          },
          {
            name: "Chocolate Chip Cookies",
            instructions: "Nisi ut aliquid ex ea commodi consequatur?",
            ingredients: ["Flour", "Chocolate Chips", "Butter", "Sugar"],
            author: "Chef Olivia",
            image: imageUrl,
          },
          {
            name: "Beef Tacos",
            instructions: "Culpa qui officia deserunt mollit anim id est laborum...",
            ingredients: ["Ground Beef", "Taco Shells", "Lettuce", "Cheese"],
            author: "Chef Ethan",
            image: imageUrl,
          },
          {
            name: "Vegan Buddha Bowl",
            instructions: "Sunt in culpa qui officia deserunt mollit anim id est laborum...",
            ingredients: ["Quinoa", "Roasted Vegetables", "Avocado", "Tahini Dressing"],
            author: "Chef Ava",
            image: imageUrl,
          },
          // Add more dummy recipes as needed
        ],
      };
    },
  };
  </script>
  