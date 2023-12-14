<template>
  <header v-if="!checkLoggedInUser">
    <ul>
      <li v-for="link in notLoggedInLinks" :key="link.text">
        <a @click.prevent="navigateTo(link.path)">{{ link.text }}</a>
      </li>
    </ul>
  </header>


  <header v-if="checkLoggedInUser">
    <ul>
      <li v-for="link in loggedInLinks" :key="link.text">
        <a @click.prevent="navigateTo(link.path)">{{ link.text }}</a>
      </li>
    </ul>
  </header>
</template>

<script>
export default {
  data() {
    return {
      isLoggedIn: false,

      notLoggedInLinks: [
        { text: 'Home', path: '/' },
        { text: 'Login', path: '/login' },
        { text: 'Register', path: '/register' }
      ],

      loggedInLinks: [
        { text: 'Home', path: '/' },
        { text: 'Add Recipes', path: '/recipes/add' },
        { text: 'View My Recipes', path: '/all/recipes' },
        { text: 'Add Meal', path: '/addMeal' },
        { text: 'Logout', path: '/logout' }
      ],
    };
  },
  methods: {
    navigateTo(route) {
      this.$router.push(route);
    },
  },
  computed: {
    checkLoggedInUser(){
      return this.$store.getters.isAuthenticated;
    },
  },

}
</script>

<style>
header {
  width: 100%;
  background-color: rgba(0, 0, 0, 0.2);
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
  background-color: #155724;
}

</style>