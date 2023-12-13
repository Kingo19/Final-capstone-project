<!-- FeatureCard.vue -->
<template>
  <div class="feature-card">
    <h2>{{ title }}</h2>
    <p>{{ description }}</p>
  </div>
</template>

<script>
export default {
  name: 'FeatureCard',
  props: {
    title: String,
    description: String,
  },
};
</script>

<style scoped>

.feature-card {
  background-color: #fdfdfd;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.feature-card h2 {
  font-size: 1.8rem;
  margin-bottom: 10px;
  color: #333;
}

.feature-card p {
  font-size: 1.2rem;
  line-height: 1.5;
  color: #666;
}
</style>
  