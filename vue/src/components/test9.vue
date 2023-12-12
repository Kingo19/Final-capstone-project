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
          <div class="tab active">INGREDIENTS</div>
          <div class="tab">PREPARATION</div>
        </div>
        <div class="recipe-details">
          <div class="ingredients-list">
            <h4>Ingredients</h4>
            <ul>
              <li>4 eggs</li>
              <li>1 can tomatoes</li>
              <li>1 onion</li>
              <!-- More ingredients -->
            </ul>
          </div>
          <div class="preparation-steps">
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
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f3f2ef;
  padding: 2rem;
}

.recipe-card {
  width: 100%;
  max-width: 900px;
  display: flex;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

.recipe-image {
  position: relative;
  width: 40%;
}

.recipe-image img {
  width: 100%;
  height: auto;
  display: block;
}

.recipe-time,
.recipe-servings {
  position: absolute;
  top: 20px;
  right: -50px;
  background: #ffd700;
  color: #fff;
  padding: 0.5rem 2rem;
  transform: rotate(45deg);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.recipe-servings {
  top: auto;
  bottom: 20px;
}

.recipe-content {
  width: 60%;
  padding: 2rem;
  background: #ffffff;
}

.recipe-title {
  font-size: 2rem;
  font-weight: bold;
  color: #333;
}

.recipe-description {
  margin-top: 1rem;
  font-size: 1rem;
  line-height: 1.5;
  color: #666;
}

.recipe-tabs {
  display: flex;
  margin-top: 2rem;
}

.tab {
  padding: 0.5rem 1rem;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.tab.active {
  background-color: #333;
  color: #fff;
}

.recipe-details {
  margin-top: 2rem;
}

.ingredients-list, .preparation-steps {
  margin-bottom: 1rem;
}

h4 {
  font-size: 1.5rem;
  color: #333;
}

ul, ol {
  list-style: none;
  padding-left: 0;
}

li {
  font-size: 1rem;
  line-height: 1.5;
  color: #666;
}

@media (max-width: 768px) {
  .recipe-card {
    flex-direction: column;
  }

  .recipe-image,
  .recipe-content {
    width: 100%;
  }

  .recipe-time,
  .recipe-servings {
    right: 20px;
    transform: rotate(0);
  }
}
</style>