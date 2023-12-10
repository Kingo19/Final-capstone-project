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

export default{
  data(){
    return{
      isLoggedIn:false
    }
  },
  methods: {
    navigateTo(route) {
      this.$router.push(route);
    },

  computed:{
    checkLoggedInUser(){
      let logged_in = this.$store.getters.isAuthenticated
      if(logged_in && !this.isLoggedIn){
        this.isLoggedInGetRecipeNames()
      }
      return logged_in
    },
  },
}

</script>

<style scoped>


</style>