<template>
  <div>
    <div class="ingredient-container">
      <div class="available-ingredients">
        <h2>Available Ingredients</h2>
        <ul>
          <li v-for="ingredient in availableIngredients" :key="ingredient.name">
            {{ ingredient.name }} - {{ ingredient.quantity }}
          </li>
        </ul>
      </div>

      <div class="ingredient-card">
        <h1>Add Ingredient</h1>
        <form @submit.prevent="addIngredient" class="ingredient-form">
          <div class="form-group">
            <label for="ingredientName">Ingredient Name :</label>
            <input
              id="ingredientName"
              v-model="newIngredient.name"
              type="text"
              placeholder="Enter ingredient name"
              class="input-field"
            />
          </div>
          <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input
              id="quantity"
              v-model="newIngredient.quantity"
              type="text"
              placeholder="Enter quantity"
              class="input-field"
            />
          </div>
          <div class="form-group">
            <label for="category">Category :</label>
            <input
              id="category"
              v-model="newIngredient.category"
              type="text"
              placeholder="Enter category"
              class="input-field"
            />
          </div>
          <div class="form-group">
            <label for="expirationDate">Expiration Date :</label>
            <input
              id="expirationDate"
              v-model="newIngredient.expirationDate"
              type="text"
              placeholder="Enter expiration date"
              class="input-field"
            />
          </div>
          <div class="form-group">
            <button type="submit" class="submit-button">Add Ingredient</button>
          </div>
          <router-link to="/" class="back-link">
            <span class="back-icon">&lt; Back to Home</span>
          </router-link>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newIngredient: {
        name: "",
        quantity: "",
        category: "",
        expirationDate: "",
      },
      availableIngredients: [
        { name: "Ingredient 1", quantity: "100g" },
        { name: "Ingredient 2", quantity: "200g" },
        { name: "Ingredient 3", quantity: "300g" },
      ],
    };
  },
  methods: {
    addIngredient() {
      if (this.isFormValid()) {
        // Add your logic to handle the new ingredient
        console.log("Added Ingredient:", this.newIngredient);
        this.newIngredient = {}; // Reset the input fields after adding
      }
    },
    isFormValid() {
      return (
        this.newIngredient.name.trim() !== "" &&
        this.newIngredient.quantity.trim() !== "" &&
        this.newIngredient.category.trim() !== "" &&
        this.newIngredient.expirationDate.trim() !== ""
      );
    },
  },
};
</script>

<style scoped>
.ingredient-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.ingredient-card {
  max-width: 400px;
  width: 100%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.available-ingredients {
  margin-right: 20px;
}

.available-ingredients h2 {
  margin-bottom: 10px;
}

.ingredient-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
  margin-bottom: 8px;
}

.input-field {
  padding: 12px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s, box-shadow 0.3s;
  margin-bottom: 8px;
}

.input-field:focus {
  border-color: #007BFF;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.2);
}

.submit-button {
  padding: 12px;
  background-color: #007BFF;
  color: #fff;
  border: none;
  cursor: pointer;
  font-size: 18px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #0056b3;
}

.back-link {
  margin-top: 20px;
  display: block;
  color: #007BFF;
  text-decoration: none;
  margin-bottom: 5rem;
}

.back-link:hover {
  text-decoration: underline;
}
</style>