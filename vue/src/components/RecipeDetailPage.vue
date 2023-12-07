<template>
  <div class="recipe-detail-container">
    <div class="head-of-container">

      <h1>{{ recipeDto.recipe.recipe_name }}</h1>

<!--      Returns to homepage-->
      <router-link to="/" class="back-link">
        <span class="back-icon">&lt; Back to Recipes</span>
      </router-link>
    </div>

    <div class="recipe-details">
      <img class="recipe-image"
           src="https://www.sandiegoprosthodontics.com/files/2018/09/Recipes-Banner.jpg"
           alt="Recipe Image" />
      <p class="instructions">{{ recipeDto.recipe.recipe_instructions }}</p>

      <div class="ingredient-list">
        <strong>Ingredients:</strong>
        <ul>
            <li v-for="ingredient in recipeDto.ingredients" :key="ingredient">{{ ingredient.ingredient_name }}</li>
        </ul>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  props: {
    recipeDto:Object
  },
};
</script>

<style scoped>
/* Add styling for recipe detail view */
.recipe-detail-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Add box shadow */
  border-radius: 8px; /* Add border-radius for a rounded appearance */
}

.head-of-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

h1 {
  margin: 0;
  font-size: 2em;
  color: #333;
}

.back-link {
  text-decoration: none;
  color: #007BFF;
  cursor: pointer;
  display: inline-block;
}

.back-icon {
  margin-right: 5px;
}

.recipe-details {
  margin-top: 20px;
}

.recipe-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* Add box shadow for the image */
}

.instructions {
  font-size: 1.2em;
  line-height: 1.6;
  color: #555;
}

.ingredient-list {
  margin-top: 20px;
}

.ingredient-list strong {
  display: block;
  margin-bottom: 10px;
  font-size: 1.2em;
  color: #333;
}

.ingredient-list ul {
  padding: 0;
}

.ingredient-list li {
  margin-bottom: 8px;
  font-size: 1em;
  color: #555;
}
</style>
