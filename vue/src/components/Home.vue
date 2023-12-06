<template>
<div class="home">
    <header>
      <div ><img src="@/assets/logo.png" class="logo"></div>
      <nav>
        <a @click.prevent="navigateTo('/')">Home</a>
        <a @click.prevent="navigateTo('/recipes')"> Add Recipes</a>
        <a @click.prevent="navigateTo('/ingredients')">Ingredients</a>
        <a @click.prevent="navigateTo('/all/recipes')">View Recipes </a>
        <a @click.prevent="navigateTo('/meal-plans')">Meal Plans</a>
      </nav>
    </header>

    <section class="hero">
      <!-- <img src="@/assets/background-image.jpeg" alt="Appetizing Food Image" class='logo'> -->
      <h1>Plan Your Meals with Ease</h1>
      <p>Discover a new way to organize your meals and create delicious recipes.</p>
    </section>

    <section class="features">
        <FeatureCard title="Add Ingredients" description="Easily add and manage your favorite ingredients. Keep track of quantities, expiration dates, and organize them into categories for effortless meal planning and grocery shopping. Simplify your cooking process and ensure you always have the ingredients you need." />

<FeatureCard title="Create Recipes" description="Create and store your own recipes for quick access. With our user-friendly recipe editor, you can input detailed instructions, ingredient lists, cooking times, and even add your own photos. Never forget a recipe again and share your culinary creations with friends and family." />

<FeatureCard title="Plan Meals" description="Efficiently plan your meals for the week with our intuitive planner. Choose from a vast library of recipes, customize your meal schedule, and drag-and-drop meals to specific days. Generate automated shopping lists based on your meal plan, save time, and ensure you have all the necessary ingredients for a successful week of delicious and nutritious meals." />
    </section>

    <section class="get-started">
      <router-link to="/signup" class="cta-button">Get Started</router-link>
    </section>



    <section class="testimonials">
      <h2 class="title">Testimonials</h2>
      <div class="testimonial-grid">
        <TestimonialComp 
          v-for="(testimonial, index) in testimonials"
          :key="index"
          :userAvatar="testimonial.userAvatar"
          :userName="testimonial.userName"
          :userRole="testimonial.userRole"
          :testimonialText="testimonial.testimonialText"
        />
      </div>
    </section>
    <footer>
      <div class="contact-info">
        <p>Contact us: contact@example.com</p>
      </div>
      <div class="footer-menu">
        <a @click.prevent="navigateTo('/about')" class="footer-link">About Us</a>
        <a @click.prevent="navigateTo('/privacy')" class="footer-link">Privacy Policy</a>
        <a @click.prevent="navigateTo('/terms')" class="footer-link">Terms of Service</a>
     
      </div>


    </footer>
  </div>
</template>




<script>
import FeatureCard from '@/components/FeatureCard.vue';
import TestimonialComp from '@/components/TestimonialComp.vue';

export default {
  name: 'HomePage',
  props: {
    msg: String
  },
  methods: {
    navigateTo(route) {
      this.$router.push(route);
    }
},

components: {
    FeatureCard,
    TestimonialComp ,
   
 
  },


  data() {
    return {
      testimonials: [
        {
          userAvatar: '@/assets/user1.jpg',
          userName: 'John Doe',
          userRole: 'Fitness Enthusiast',
          testimonialText: 'I love using this meal planner app. It has made meal planning so much simpler and enjoyable. The variety of recipes and ingredients available is impressive. It has truly transformed the way I approach meal preparation. Highly recommended for anyone looking to improve their eating habits and culinary skills!',
        },
        {
          
          userName: 'Jane Smith',
          userRole: 'Food Blogger',
          testimonialText: 'The meal recipes in this app are fantastic! I have tried so many and they never disappoint. The step-by-step instructions and clear ingredient lists make it easy to follow along and create delicious meals. This app has become my go-to resource for recipe inspiration and meal planning. Kudos to the developers for creating such a user-friendly and valuable tool!',
        },
        {
          
          userName: 'Bob Johnson',
          userRole: 'Busy Professional',
          testimonialText: 'As a busy professional, this app has saved me so much time in planning and preparing meals. The ability to customize meal plans and generate shopping lists has been a game-changer. The recipes are not only quick and easy to make, but they are also packed with flavor. I no longer have to stress about what to cook after a long day at work. This app has simplified my life and helped me maintain a healthy eating routine!',
        }
      ],
    };
  },
        
    
}
</script>


<style scoped>
/* App-wide styles (styles.css) */
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
 
}
header {
  background-color: #333;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
padding: 0.4rem 0;
}

nav ul {
  list-style: none;
  display: flex;
}

nav li {
  margin-right: 20px;
}

nav a {
  text-decoration: none;
  color: #fff;
}

/* Hero section styles */
.hero {
  position: relative;
  text-align: center;
  color: #333;
}

.hero img {
  width: 100%;
  height: auto;
}

.hero h1 {
  font-size: 2.5rem;
  margin-bottom: 20px;
}

.hero p {
  font-size: 1.2rem;
}

/* Features section styles */
.features {
  display: flex;
  justify-content: space-around;
  margin: 2rem 0;
}

.feature-card {
  background-color: #f8f8f8;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.feature-card h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

/* Get Started section styles */
.get-started {
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

/* Footer styles */
footer {
  background-color: #333;
  color: #fff;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.contact-info p {
  margin: 0;
}

.footer-menu ul {
  list-style: none;
  display: flex;
}

.footer-menu li {
  margin-right: 20px;
}

.footer-menu a {
  text-decoration: none;
  color: #fff;
}


a {
  cursor: pointer;
  background: none;
  border: none;
  color: #007BFF; /* Adjust the color to your preference */
  text-decoration: underline;
  font-size: 1rem;
  margin-right: 20px;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
}

.logo{
    width: 50px;
    height: 50px;
    border-radius: 50%;
}

  /* Testimonials section styles */
  .testimonials {
    margin: 2rem 0;
  }

  .testimonial-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 40px;
    margin-left:  70px;
  }
  .title{
   margin-left: 50px;
   
  }

</style>
