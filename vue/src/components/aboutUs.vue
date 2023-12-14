<template>
  <div id="about">
  <div class="about-us">
    <h1>Welcome to Our World of Innovation and Empathy</h1>
    <p>We're more than just a team of programmers – we're a family of innovative professionals, united by our passion for technology and our commitment to making a positive impact in the world. As proud graduates of the Tech Elevator program, we stand at the threshold of a new era in programming, ready to make our mark.</p>

    <h2>Our Journey - From Learning to Leading</h2>
    <p>Our journey began in the vibrant classrooms of Tech Elevator, where we honed our skills in programming and technology. Here, each line of code we wrote was not just a task, but a step towards our dream – a dream of creating technology that touches lives. Our cohort has been more than just a learning experience; it has been a crucible forging us into the innovative professionals we are today.</p>

    <h2>Our Mission - Programming with Purpose</h2>
    <p>We believe that technology should be more than just functional – it should be a source of comfort, happiness, and well-being. Our mission is to harness the power of programming to create solutions that not only solve problems but also enhance the human experience. Every project we undertake is imbued with this philosophy, aiming to bring a positive change in the lives of our customers.</p>

    <h2>Our Capstone - A Testament to Our Passion</h2>
    <p>Our capstone project is not just a culmination of our learning; it's a showcase of our passion, dedication, and commitment to excellence. It's a testament to our journey from learners to leaders in the tech world. This project embodies our innovative spirit and our relentless pursuit of excellence in programming.</p>

    <h2>Our Promise - Your Satisfaction, Our Pride</h2>
    <p>Your experience and satisfaction are at the heart of everything we do. We're not just building software; we're crafting experiences that resonate with your needs and aspirations. Our commitment to customer well-being is unwavering, and we strive to ensure that every interaction you have with our products leaves you feeling happy and content.</p>

    <h2>Connect with Us - Let's Create Magic Together</h2>
    <p>Join us on this exhilarating journey as we step into the world, armed with code, creativity, and compassion. Whether you're looking for innovative solutions or a partnership to bring your ideas to life, we are here to make it happen. Together, let's create technology that not only functions but also cares.</p>
  </div>
  </div>

</template>

<script>

export default {
  data(){
    return{

    }
  }
}

</script>

<style scoped>

#about{
  background: url("../assets/front.png");
  width: 100%;
  height: 100%;
}


.about-us {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  max-width: 900px;
  margin: 40px auto;
  padding: 20px;
  background-color: #f8f8f8;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.about-us h1 {
  color: #2a3f5f;
  font-size: 2.2em;
  text-align: center;
  margin-bottom: 20px;
}

.about-us h2 {
  color: #3a6ea5;
  font-size: 1.6em;
  margin-top: 30px;
  margin-bottom: 15px;
}

.about-us p {
  color: #4a4a4a;
  font-size: 1em;
  line-height: 1.6;
  text-align: justify;
  margin-bottom: 20px;
}

@media screen and (max-width: 768px) {
  .about-us {
    padding: 15px;
    max-width: 100%;
  }

  .about-us h1 {
    font-size: 1.8em;
  }

  .about-us h2 {
    font-size: 1.4em;
  }
}
</style>