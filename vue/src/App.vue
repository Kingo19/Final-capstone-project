<template>

  <section class="layout">
    <header-view class="appHeader" />
    <router-view class="innerBody"/>
    <footer-view class="appFooter"/>
  </section>

</template>
<script>
import headerView from "@/views/HeaderView.vue";
import FooterView from "@/views/FooterView.vue";

export default {
    components:{
      headerView,
      FooterView
    }
}

</script>

<style>

@font-face {
  font-family: "MV Boli";
  src: url("resources/mvboli.ttf");
}

body{
  font-family: "MV Boli";
   margin: 0;
  padding: 0;
  box-sizing: border-box;
  height: 100vh;
  width: 100vw;
}

 .layout {

   min-width: 500px;
   margin: auto;

  display: grid;
  grid-template-rows: auto 1fr 0.1fr;
  grid-template-areas:
    "appHeader"
    "innerBody"
    "appFooter";
  gap: 5px;
}

.appHeader{
  grid-area: appHeader;
}
.innerBody {
  grid-area: innerBody;
/*  min-height: 800px;*/
/*  background: url(https://druryjeff.github.io/better-from-the-source/img/wood.jpg) 50% 50%;*/
}

.appFooter {
  grid-area: appFooter;
  max-height: 200px;
}

</style>