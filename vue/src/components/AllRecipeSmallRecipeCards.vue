<template>
  <div class="card-container">
    <router-link
        v-for="recipe in recipes"
        :key="recipe.id"
        :to="{ name: 'RecipeDetail', params: { recipeName: recipe.name } }"
        class="cardRecipe"
        tag="div"
    >
      <div class="cont_photo">
        <img :src="recipe.image" alt="Recipe Image" />
        <div class="cont_detalles">
          <h3>{{ recipe.name }}</h3>
          <p>{{ recipe.description }}</p>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  props:{
    recipeDto:Object
  },
  data() {
    return {
      isModalOpen: {},
      recipes: [
        {
          id: 1,
          name: 'Shakshuka With Feta',
          image: 'https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg',
          description: 'A delightful blend of eggs, tomatoes, and feta.',
          ingredients: ['Eggs', 'Tomatoes', 'Feta Cheese', 'Onion', 'Bell Pepper'],
          instructions: 'Heat oil, add ingredients, simmer and serve.'
        },
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
  display: block;
  width: calc(30% - 20px); /* Adjusts card width */
  position: relative; /* Required for absolute positioning of children */

}

.cardRecipe {
  cursor: pointer; /* Indicates the item is clickable */
/*  margin: 10px;*/
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
/*  max-height: 40px; !* Set a maximum height *!*/
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
}

.cont_photo img {
  width: 100%;
  height: auto;
  border-radius: 5px;
  object-fit: cover; /* Ensures the image covers the area without distortion */
  max-height: 200px; /* Adjust the height as needed */
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
}

.cont_detalles {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  transition: all 0.5s ease; /* Smooth transition for expanding effect */
}

.cont_detalles {
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px;
}


/* This will expand the ingredients section */
.cardRecipe:hover .cont_text_ingredients {
  max-height: 200px; /* Adjust this value based on content */
}

</style>

<!--
<style scoped>
.card-container {
  padding-top: 10px;
  max-width: 25%;
  max-height: 40%;
}

.cardRecipe {
  position: relative;
}

.cont_photo img {
  width: 100%;
  display: block;
  border-radius: 5px;
  object-fit: cover;
  position: relative;
}

.cont_detalles {
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px;
  position: absolute;
  bottom: 0;
  width: 93%;
}
</style>

-->

