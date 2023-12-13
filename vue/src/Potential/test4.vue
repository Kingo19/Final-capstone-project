<template>
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
</template>
<script>
export default {
  data() {
    return {
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
  width: calc(33% - 20px); /* Adjusts card width */
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  position: relative; /* Required for absolute positioning of children */
}

.cont_photo img {
  width: 100%;
  height: auto;
  display: block;
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