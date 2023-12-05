<!-- Testimonial.vue -->
<template>
    <div class="testimonial">
      <FeatureCard title="Add Ingredients" description="Easily add and manage your favorite ingredients. Keep track of quantities, expiration dates, and organize them into categories for effortless meal planning and grocery shopping. Simplify your cooking process and ensure you always have the ingredients you need." />

    </div>
  </template>
  
  <script>
  import FeatureCard from '@/components/FeatureCard.vue';
  export default {

    name: 'TestimonialCompPage',
   
    props: {
      
      userName: String,
      userRole: String,
      testimonialText: String,
    },
    components: {
    FeatureCard,
    
   
 
  },
   
  };
  </script>
  
  <style scoped>
  /* Testimonial component styles */
  .testimonial {
    background-color: #f8f8f8;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
  }
  
  .user-info {
    display: flex;
    align-items: center;
  }
  
  .avatar {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-right: 10px;
  }
  
  .user-details {
    flex-grow: 1;
  }
  
  .testimonial-text {
    margin-top: 10px;
  }
  </style>
  