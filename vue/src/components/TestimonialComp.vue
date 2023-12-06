<template>
  <div class="testimonial">
    <div v-for="testimonial in testimonials" :key="testimonial.persona" class="testimonial-item">
      <div class="user-info">
        <div class="avatar"></div>
        <div class="user-details">
          <h3>{{ testimonial.persona }}</h3>
          <p class="user-role">Customer</p>
        </div>
      </div>
      <p class="testimonial-text">{{ testimonial.review }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TestimonialCompPage',
  data() {
    return {
      testimonials: [
        {
          persona: "John Doe",
          review: "This ingredient tracking feature has been a game-changer for me! It helps me stay organized and plan my meals effectively. Highly recommended!",
        },
        {
          persona: "Jane Smith",
          review: "As a busy working professional, managing ingredients has always been a hassle. But with this app, I can easily keep track of what I have and what I need. It saves me time and stress!",
        },
        {
          persona: "David Johnson",
          review: "I love cooking, but I often forget to buy essential ingredients. This app solves that problem by allowing me to add ingredients and create shopping lists. It's a must-have for any home cook!",
        },
      ],
    };
  },
};
</script>

<style scoped>

.testimonial {
  display: flex;
  justify-content: center;
  align-items: center;
}

.testimonial-item {
  width: 800px; /* Adjust the width as per your requirement */
  background-color: #f8f8f8;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin: 10px;
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
  background-color: #ccc; /* Add your avatar image or background color here */
}

.user-details {
  flex-grow: 1;
}

h3 {
  margin: 0;
}

.user-role {
  margin: 0;
  color: #888;
  font-size: 14px;
}

.testimonial-text {
  margin-top: 10px;
}
</style>
