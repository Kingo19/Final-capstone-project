<template>
  <div>
    <button class="collapsible" @click="toggleCollapse">{{ title }}</button>
    <div class="content" v-show="isCollapsed">
      <slot></slot>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      isCollapsed: false,
      };
      },
      props: {
      title: String,
      },

      methods: {
      toggleCollapse() {
      this.isCollapsed = !this.isCollapsed;
      },
    },
};

</script>
<style>
.collapsible {
  background-color: #f1f1f1;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #ccc;
}

.content {
  padding: 0 18px;
  display: none;
  overflow: hidden;
  background-color: #f1f1f1;
}
</style>
