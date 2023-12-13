<template>
  <div class="card-container">
    <router-link
        v-for="recipe in recipes"
        :key="recipe.id"
        :to="{ name: 'RecipeDetail', params: { recipeName: recipeDto.recipe.recipe_name } }"
        class="cardRecipe"
        tag="div"
    >
      <div class="cont_photo">
        <img :src="recipe.image" alt="Recipe Image" />
        <div class="cont_detalles">
          <h3>{{ recipeDto.recipe.recipe_name }}</h3>
          <p>{{ recipeDto.recipe.recipe_instructions }}</p>
        </div>
      </div>
    </router-link>
  </div>
</template>

<!--<template>
  <div class="card-container">
    <div v-for="recipe in recipes" :key="recipe.id" class="cardRecipe">
      <div @click="toggleModal(recipe.id)">
        <div class="cont_photo">
          <img :src="recipe.image" alt="Recipe Image" />
          <div class="cont_detalles">
            <h3>{{ recipe.name }}</h3>
            <p>{{ recipe.description }}</p>
          </div>
        </div>
        <div v-if="isModalOpen[recipe.id]" class="cont_text_ingredients">
          <h4>Ingredients</h4>
          <ul>
            <li v-for="ingredient in recipe.ingredients" :key="ingredient">{{ ingredient }}</li>
          </ul>
          <h4>Recipe</h4>
          <p>{{ recipe.instructions }}</p>
        </div>
      </div>
    </div>
  </div>
</template>-->
<script>
export default {
  props:{
    recipeDto:Object
  },
  data() {
    return {
/*      recipeDto:
          {recipe:{
              "recipe_name": "Chocolate Cake",
              "recipe_instructions": "Instructions for Chocolate Cake.",
            },
            "ingredients": [
              {"ingredient_name": "Flour"},
              {"ingredient_name": "Sugar"},
              {"ingredient_name": "Cocoa Powder"}
            ]
          },*/
      isModalOpen: {},
      recipes: [
        // Sample recipe data
        {
          id: 1,
          name: 'Shakshuka With Feta',
          image: 'https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg',
          description: 'A delightful blend of eggs, tomatoes, and feta.',
          ingredients: ['Eggs', 'Tomatoes', 'Feta Cheese', 'Onion', 'Bell Pepper'],
          instructions: 'Heat oil, add ingredients, simmer and serve.'
        },
        // Additional recipe objects
      ],
    };
  },
  created() {
    this.recipes.forEach(recipe => {
      this.isModalOpen[recipe.id] = false;
    });

    const recipeName = this.$route.params.recipeName;
  },
  methods: {
    toggleModal(id) {
      this.isModalOpen[id] = !this.isModalOpen[id];
    }
  },
};
</script>

<style scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 20px; /* Adds space between cards */
}

.cardRecipe {
  width: calc(30% - 20px); /* Adjusts card width */
  cursor: pointer; /* Indicates the item is clickable */
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  position: relative; /* Required for absolute positioning of children */
  max-height: 400px; /* Set a maximum height */
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
}

.cont_photo img {
/*  width: 100%;
  height: auto;
  display: block;*/

  width: 100%;
  height: auto;
  display: block;
  border-radius: 5px;
  object-fit: cover; /* Ensures the image covers the area without distortion */
  max-height: 200px; /* Adjust the height as needed */

}

.cont_detalles, .cont_text_ingredients {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  transition: all 0.5s ease; /* Smooth transition for expanding effect */
}

.cont_detalles {
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px;
}

.cont_text_ingredients {
  background: #f9f9f9;
  color: #333;
  padding: 10px;
  max-height: 0;
  overflow: hidden;
}

/* This will expand the ingredients section */
.cardRecipe:hover .cont_text_ingredients {
  max-height: 200px; /* Adjust this value based on content */
}

.cont_text_ingredients ul {
  padding: 0;
  list-style: none;
}
</style>
