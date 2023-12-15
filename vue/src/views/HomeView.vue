<template>
  <div class="home">
    <!-- BODY  ====  BODY  ===  BODY  === BODY-->

    <section class="upper-body-text" v-if="!checkLoggedInUser">
      <!-- <img src="@/assets/background-image.jpeg" alt="Appetizing Food Image" class='logo'> -->
      <div>
      <h1>Plan Your Meals with Ease</h1>
      <p>Discover a new way to organize your meals and create delicious recipes.</p>
      </div>
      <div class="test"><router-link to="/register" class="cta-button">Get Started</router-link></div>
    </section>


    <section class="upper-body-text" v-if="checkLoggedInUser">
      <!-- <img src="@/assets/background-image.jpeg" alt="Appetizing Food Image" class='logo'> -->
      <div>
        <h1>WELCOME BACK</h1>
        <br>
        <h1>{{ getUser() }}</h1>
      </div>
    </section>

<!--    <section class="features">
      <FeatureCard title="Add Ingredients" :description="Add_Ingredients"/>
      <FeatureCard title="Create Recipes" :description="Create_Recipes"/>
      <FeatureCard title="Plan Meals" :description="Plan_Meals"/>
    </section>-->

<!--    <section class="testimonials">
      <h2 class="title">Testimonials</h2>
      <TestimonialComp/>
    </section>-->


    <!-- FOOTER  ====  FOOTER  ===  FOOTER  === FOOTER --><!-- FOOTER  ====  FOOTER  ===  FOOTER  === FOOTER -->

  </div>
</template>

<script>
import FeatureCard
  from '../components/FeatureCard.vue';
import TestimonialComp
  from '../components/TestimonialComp.vue';

export default {

  computed: {
    checkLoggedInUser(){
      return this.$store.getters.isAuthenticated;
    },
  },
  name: 'HomePage',
  data() {
    return {

      Add_Ingredients: "Easily add and manage your favorite ingredients. " +
          "Keep track of quantities, expiration dates, " +
          "and organize them into categories for effortless meal planning and grocery shopping. " +
          "Simplify your cooking process and ensure you always have the ingredients you need.",

      Create_Recipes: "Create and store your own recipes for quick access. " +
          "With our user-friendly recipe editor, " +
          "you can input detailed instructions, ingredient lists, cooking times, and even add your own photos. " +
          "Never forget a recipe again and share your culinary creations with friends and family.",

      Plan_Meals: "Efficiently plan your meals for the week with our intuitive planner. " +
          "Choose from a vast library of recipes, customize your meal schedule, and drag-and-",

      testimonials: [
        {
          userAvatar: '@/assets/user1.jpg',
          userName: 'John Doe',
          userRole: 'Fitness Enthusiast',
          testimonialText: 'I love using this meal planner app. It has made meal planning so much simpler and enjoyable. ' +
              'The variety of recipes and ingredients available is impressive. It has truly transformed the way ' +
              'I approach meal preparation. Highly recommended for anyone looking to improve their eating habits and culinary skills!',
        },
        {

          userName: 'Jane Smith',
          userRole: 'Food Blogger',
          testimonialText: 'The meal recipes in this app are fantastic! ' +
              'I have tried so many and they never disappoint. ' +
              'The step-by-step instructions and clear ingredient lists ' +
              'make it easy to follow along and create delicious meals. ' +
              'This app has become my go-to resource for recipe inspiration and meal planning. ' +
              'Kudos to the developers for creating such a user-friendly and valuable tool!',
        },
        {

          userName: 'Bob Johnson',
          userRole: 'Busy Professional',
          testimonialText: 'As a busy professional, this app has saved me so much time in planning and preparing meals. ' +
              'The ability to customize meal plans and generate shopping lists has been a game-changer. ' +
              'The recipes are not only quick and easy to make, but they are also packed with flavor. ' +
              'I no longer have to stress about what to cook after a long day at work. ' +
              'This app has simplified my life and helped me maintain a healthy eating routine!',
        } //End object
      ], //testimonials
    }; //End Return Object
  }, //End Data Property
  components: {
    FeatureCard,
    TestimonialComp,
  },

  methods:{
    getUser(){
      return this.$store.state.user.username
    }

  }
}
</script>

<style scoped>


.home{
  background: url("../assets/rauls.png");
  background-size: cover;
  height: 1024px;
}
  /* App-wide styles (styles.css) */
/* Hero section styles */
.upper-body-text {
  display: flex;
  flex-wrap: wrap;
  justify-self: flex-end;
  align-items: flex-end;
  height: 600px;
  width: 400px;
  background-color: white;
  font-weight: 600;
  color: #000000;
  margin: 15px;
}

.test{
  align-content: flex-end;
/*  justify-content: flex-end;
  align-content: flex-end;*/
}


.upper-body-text img {
  width: 100%;
  height: auto;
}

.upper-body-text h1 {
  font-size: 2.5rem;
  margin-bottom: 20px;
}

.upper-body-text p {
  font-size: 1.2rem;
}

/* Features section styles */
.features {
  display: flex;
  justify-content: center;
  margin: 2px;
  gap: 10px;
}

.feature-card {
  background-color: #f8f8f8;
  width: 450px;
  height: 300px;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.feature-card h2 {
  font-size: 1rem;
  margin-bottom: 10px;
}

/* Get Started section styles */
.sign-up {
  text-align: center;
  margin: 2rem 0;
}

.cta-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
  text-decoration: none;
  font-size: 1.2rem;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.cta-button:hover {
  background-color: #555;
}



/* Testimonials section styles */
.testimonials {
  margin: 2rem 0;
}

.testimonial-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 40px;
  margin-left: 70px;
}

.title {
  margin-left: 50px;
  color: white;
}


</style>
