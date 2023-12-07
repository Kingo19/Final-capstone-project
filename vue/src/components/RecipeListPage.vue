<template>
    <div class="recipe-list-container">
      <h1>My Delicious Recipes</h1>
<!--  -->
<!--      &lt;!&ndash; Sorting Options &ndash;&gt;-->
<!--      <div class="sorting-options">-->
<!--        <label for="sort">Sort By:</label>-->
<!--        <select v-model="sortCriteria" @change="sortRecipes">-->
<!--          <option value="name">Name</option>-->
<!--          <option value="author">Author</option>-->
<!--          -->
<!--        </select>-->
      </div>
<!--  -->
<!--      &lt;!&ndash; Filtering Options &ndash;&gt;-->
<!--      <div class="filtering-options">-->
<!--        <label for="category">Filter By Category:</label>-->
<!--        <select v-model="selectedCategory" @change="filterRecipes">-->
<!--          <option value="">All</option>-->
<!--          <option v-for="category in uniqueCategories" :key="category" :value="category">{{ category }}</option>-->
<!--        </select>-->
<!--      </div>-->
<!--  -->
<!--      <div class="recipe-cards">-->
<!--        <router-link-->
<!--          v-for="(recipe, index) in filteredRecipes"-->
<!--          :key="index"-->
<!--          :to="'/all/recipe/' + index"-->
<!--          class="recipe-card-link"-->
<!--        >-->
<!--          <div class="recipe-card">-->
<!--            <div class="card-header">-->
<!--              <h2>{{ recipe.name }}</h2>-->
<!--              <p class="author">By {{ recipe.author }}</p>-->
<!--            </div>-->
<!--            <img :src="recipe.image" alt="Recipe Image" class="recipe-image" />-->
<!--            <div class="recipe-details">-->
<!--              <p class="instructions">{{ recipe.instructions.slice(0, 150) }}...</p>-->
<!--              <div class="ingredient-list">-->
<!--                <strong>Ingredients:</strong>-->
<!--                <ul>-->
<!--                  <li v-for="(ingredient, i) in recipe.ingredients" :key="i">{{ ingredient }}</li>-->
<!--                </ul>-->
<!--              </div>-->
<!--            -->
<!--             -->
<!--            </div>-->
<!--          </div>-->
<!--        </router-link>-->
<!--      </div>-->
<!--    </div>-->
  </template>
  
  <script>
  export default {
/*    props: {
      recipes: Array,
    },*/
    data() {
      return {
        sortCriteria: 'name',
        selectedCategory: '',
      };
    },
    computed: {
  //       uniqueCategories() {
  //   const categories = new Set();
  //   this.recipes.forEach((recipe) => {
  //     // Check if the 'categories' property exists and is an array
  //     if (recipe.categories && Array.isArray(recipe.categories)) {
  //       recipe.categories.forEach((category) => {
  //         categories.add(category);
  //       });
  //     }
  //   });
  //   return Array.from(categories);
  // },
  //     filteredRecipes() {
  //       let recipes = [...this.recipes];
  //
  //       // Filtering
  //       if (this.selectedCategory) {
  //         recipes = recipes.filter((recipe) => recipe.categories.includes(this.selectedCategory));
  //       }
  //
  //       // Sorting
  //       recipes.sort((a, b) => {
  //         const criteriaA = a[this.sortCriteria].toLowerCase();
  //         const criteriaB = b[this.sortCriteria].toLowerCase();
  //
  //         if (criteriaA < criteriaB) return -1;
  //         if (criteriaA > criteriaB) return 1;
  //         return 0;
  //       });
  //
  //       return recipes;
  //     },
    },
    methods: {
      sortRecipes() {
       
      },
      filterRecipes() {
       
      },
      
    },
  };
  </script>
  
  <style scoped>
  /* Add styling for recipe list view */
  .recipe-list-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .recipe-cards {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
  }
  
  .recipe-card-link {
    text-decoration: none;
    color: inherit;
  }
  
  .recipe-card {
    max-width: 300px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    background-color: #fff;
    overflow: hidden;
    transition: transform 0.2s;
  }
  
  .recipe-card:hover {
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
    transform: scale(1.05);
  }
  
  .card-header {
    background-color: #007BFF;
    color: #fff;
    padding: 10px;
    border-bottom: 1px solid #ddd;
  }
  
  .card-header h2 {
    margin: 0;
  }
  
  .author {
    margin-top: 5px;
  }
  
  .recipe-image {
    width: 100%;
    height: 150px;
    object-fit: cover;
  }
  
  .recipe-details {
    padding: 10px;
  }
  
  .instructions {
    margin-bottom: 10px;
  }
  
  .ingredient-list {
    margin-top: 10px;
  }
  
  .ingredient-list strong {
    display: block;
    margin-bottom: 5px;
  }
  
  .ingredient-list ul {
    padding: 0;
    list-style: none;
  }
  
  .ingredient-list li {
    margin-bottom: 5px;
  }

  .sorting-options,
.filtering-options {
  margin-bottom: 10px;
}

.sorting-options label,
.filtering-options label {
  margin-right: 10px;
}

/* Favorite Button Styling */
button {
  background-color: #007BFF;
  color: #fff;
  border: none;
  padding: 5px 10px;
  margin-top: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

.is-favorite {
  background-color: #ff9800;
  color: #333;
}
  </style>
  