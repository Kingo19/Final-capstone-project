<template>
  <div class="card-container" @click="changeViewToSingle">
    <router-link
        v-for="recipe in recipes"
        :key="recipe.id"
        :to="{ name: 'RecipeDetail', params: { recipeName: recipeDto.recipe.recipe_name  } }"
        class="cardRecipe"
        tag="div"
    >
      <div class="cont_photo">
        <img :src="recipe.image" alt="Recipe Image" />
        <div class="cont_detalles">
          <h3>{{ recipeDto.recipe.recipe_name }}</h3>
          <p>{{ recipeDto.recipe.recipe_instructions}}</p>
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
    },

    changeViewToSingle(){
      this.$store.commit("switchingTheView")
    }
  },
};
</script>

<style scoped>


.card-container {
/*  background: url(https://druryjeff.github.io/better-from-the-source/img/wood.jpg) 50% 50%;*/
  width: calc(30% - 20px); /* Adjusts card width */
}

.cardRecipe {
  display: block;
  cursor: pointer; /* Indicates the item is clickable */
  height: 300px;
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
  position: relative; /* Required for absolute positioning of children */
}

.cont_photo img {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  object-fit: cover; /* Ensures the image covers the area without distortion */
  max-height: 200px; /* Adjust the height as needed */
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
}

.cont_detalles {
  position: absolute;
  width: 100%;
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


