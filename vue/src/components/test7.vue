
<template>
  <div class="recipe-page">
    <div class="recipe-card">
      <div class="recipe-image">
        <img src="https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg" alt="Shakshuka With Feta" />
        <div class="recipe-time">50 MINS</div>
        <div class="recipe-servings">4 SERVINGS</div>
      </div>
      <div class="recipe-content">
        <div class="recipe-title">Shakshuka With Feta</div>
        <div class="recipe-description">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sagittis est aliquam, sed faucibus massa lobortis. Maecenas non est justo.
        </div>
        <div class="recipe-tabs">
          <div class="tab" :class="{ active: activeTab === 'ingredients' }" @click="activeTab = 'ingredients'">INGREDIENTS</div>
          <div class="tab" :class="{ active: activeTab === 'preparation' }" @click="activeTab = 'preparation'">PREPARATION</div>
        </div>
        <div class="recipe-details">
          <div class="ingredients-list" v-show="activeTab === 'ingredients'">
            <h4>Ingredients</h4>
            <ul>
              <li>4 eggs</li>
              <li>1 can tomatoes</li>
              <li>1 onion</li>
              <!-- More ingredients -->
            </ul>
          </div>
          <div class="preparation-steps" v-show="activeTab === 'preparation'">
            <h4>Preparation</h4>
            <ol>
              <li>Heat oven to 375 degrees.</li>
              <li>Heat oil in a large skillet over medium-low heat. Add onion and bell pepper...</li>
              <!-- More steps -->
            </ol>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: 'ingredients', // default tab
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
.recipe-page {
  max-width: 800px;
  margin: auto;
  padding: 20px;
}

.recipe-card {
  display: flex;
  flex-direction: row;
  align-items: stretch;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
  background: #fff;
}

.recipe-image {
  flex-basis: 40%;
  position: relative;
}

.recipe-image img {
  width: 100%;
  height: auto;
  display: block;
}

.recipe-time,
.recipe-servings {
  position: absolute;
  background: rgba(255,253,112,0.8);
  padding: 8px;
  border-radius: 50%;
  color: #333;
  left: 20px;
  top: 20px;
}

.recipe-servings {
  left: auto;
  right: 20px;
  top: 20px;
}

.recipe-content {
  flex-basis: 60%;
  padding: 20px;
}

.recipe-title {
  font-size: 24px;
  font-weight: bold;
}

.recipe-description {
  margin: 10px 0;
}

.recipe-tabs {
  display: flex;
  margin-bottom: 10px;
}

.tab {
  padding: 10px;
  cursor: pointer;
  border-bottom: 2px solid transparent;
}

.tab.active {
  border-bottom: 2px solid #ED346C;
}

.recipe-details {
  display: none;
}

.ingredients-list,
.preparation-steps {
  display: none;
}

[v-show="true"] {
  display: block;
}

@media (max-width: 768px) {
  .recipe-card {
    flex-direction: column;
  }

  .recipe-image,
  .recipe-content {
    flex-basis: auto;
  }

  .recipe-image {
    order: 2;
  }

  .recipe-content {
    order: 1;
  }
}
</style>