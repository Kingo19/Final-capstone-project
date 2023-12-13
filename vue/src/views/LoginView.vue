<template>
  <div id="login" class="login-container">
    <form v-on:submit.prevent="login" class="login-form">
      <h1>Please Sign In</h1>

      <div role="alert" v-if="invalidCredentials" class="error-message">
        Invalid username and password!
      </div>

      <div role="alert" v-if="$route.query.registration" class="success-message">
        Thank you for registering, please sign in.
      </div>

      <div class="form-input-group">
       
        <input placeholder="username" type="text" id="username" v-model="user.username" required />
      </div>
      <br/>

      <div class="form-input-group">
        
        <input placeholder="password" type="password" id="password" v-model="user.password" required />
      </div>

      <div class="form-input-group">
        <label class="remember-me">
          <input type="checkbox" v-model="user.rememberMe" /> 
        </label>
        <a href="#" class="forgot-password"> Remember me Forgot password?</a>
      </div>

      <button type="submit" class="submit-button">Login</button>

      <p>
        <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
  <div class="imageView"> <ImageView> </ImageView> </div>
</template>

<script>
import authService from "../services/AuthService";
import ImageView from "./ImageView.vue";
export default {
  components: {ImageView},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.login-container {
  background: url('https://gico.io/spcica/images/resource/login.jpg') top right/contain no-repeat;
  height: 100vh;
  margin: 20px 80px;
  display: flex;
  justify-content: flex-end;
  align-items: flex-start;
}

.login-form {
  width: 40%;
  height: 80%;
  max-width: calc(100% - 40px);
  padding: 20px;
  background-color: rgb(234, 232, 227);
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.error-message,
.success-message {
  margin-bottom: 1rem;
  padding: 0.5rem;
  border-radius: 4px;
}

.error-message {
  background-color: #ffdddd;
  color: #ff0000;
}

.success-message {
  background-color: #d4edda;
  color: #155724;
}

.form-input-group {
  width: 100%;
  margin-bottom: 1rem;
  display: flex; /* Added to enable flex layout */
  align-items: center; /* Center items vertically within the flex container */
}

label {
  margin-right: 0.5rem;
}

.form-input-group input {
  width: calc(100% - 1rem);
  height: 3rem;
}

.remember-me {
  
  margin-bottom: 0.5rem;
  display: flex;
  align-items: center;
}

.remember-me input {
  margin-right: 0.5rem;
  align-self: center;
  transform: scale(1.5);
}

.forgot-password {
  margin-top: 0.5rem;
}

.submit-button {
  background-color: #d35e16;
  border-radius: 100%;
  width:100px;
  height:60px;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.imageView {
  margin-top: 100px;
  background: white;
}
.submit-button:hover {
  background-color: #45a049;
}
</style>
