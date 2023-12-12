<template>
  <header>
    <div><img
        src="../assets/logo.png"
        class="logo"></div>

    <nav v-if="!checkLoggedInUser">
      <a @click.prevent="navigateTo('/')">Home</a>
      <a @click.prevent="navigateTo('/login')">Login</a>
      <a @click.prevent="navigateTo('/register')">Register</a>
    </nav>

    <nav v-if="checkLoggedInUser">
      <a @click.prevent="navigateTo('/recipes/add')">Add Recipes</a>
      <a @click.prevent="navigateTo('/all/recipe')">View My Recipes</a>
      <a @click.prevent="navigateTo('/logout')">Logout</a>
    </nav>
  </header>
</template>

<script>

import recipeService from "@/services/RecipeService";

export default {
  data() {
    return {
      isLoggedIn: false
    }
  },
  methods: {
    navigateTo(route) {
      this.$router.push(route);
    },
  },

  computed: {
    checkLoggedInUser(){
      return this.$store.getters.isAuthenticated
    }
  },
}

</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  background: url(https://druryjeff.github.io/better-from-the-source/img/wood.jpg) 50% 50%;
}




header {
  width: 100%;
  background-color: rgba(0, 0, 0, .2);
}


header ul {
  text-align: center;
  padding: 0; /* Added to remove default padding */
  margin: 0; /* Added to remove default margin */
}


header ul li {
  list-style: none;
  display: inline-block;
}


header ul li a {
  display: block;
  text-decoration: none;
  text-transform: uppercase;
  color: #fff;
  font-size: 20px; /* Corrected '2epx' to '20px' */
  font-family: 'Raleway', sans-serif;
  letter-spacing: 2px;
  font-weight: 600;
  padding: 25px;
  transition: all ease 0.5s; /* Corrected 'e.5s' to '0.5s' */
}


header ul li a:hover {
  background-color: #211b4385;
}


</style>