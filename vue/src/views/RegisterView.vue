<template>
  <div id="register" class="signup-container">
    <form v-on:submit.prevent="register" @focusin="clearErrors" class="fade-in">
      <h1>Create Account</h1>

      <div role="alert" v-if="registrationErrors" class="error-message">
        <i class="fas fa-exclamation-circle"></i> {{ registrationErrorMsg }}
      </div>

      <div class="form-input-group">
        <label for="fname"></label>
        <input
          type="text"
          id="fname"
          placeholder="Firstname"
          required
          autofocus
        />
      </div>

      <div class="form-input-group">
        <label for="lname"></label>
        <input type="text" id="lname" placeholder="Lastname" required />
      </div>

      <div class="form-input-group">
        <label for="email"></label>
        <input type="email" id="email" placeholder="example@gmail.com" required />
      </div>

      <div class="form-input-group">
        <label for="username"><i class="fas fa-user"></i></label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          placeholder="Username"
          required
          autofocus
        />
      </div>

      <div class="form-input-group">
        <label for="password"><i class="fas fa-lock"></i></label>
        <input
          type="password"
          id="password"
          v-model="user.password"
          placeholder="Password"
          required
        />
      </div>

      <div class="form-input-group">
        <label for="confirmPassword"><i class="fas fa-lock"></i></label>
        <input
          type="password"
          id="confirmPassword"
          v-model="user.confirmPassword"
          placeholder="Confirm Password"
          required
        />
      </div>

      <button type="submit" class="submit-button">Create Account</button>

      <p>
        <router-link v-bind:to="{ name: 'login' }"
          >Already have an account? Log in.</router-link
        >
      </p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.signup-container {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: url("https://noahhelps.org/wp-content/uploads/2021/03/March_Meal-Prep-1030x515.jpeg")
    center/cover;
}

.signup-container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.2));
}

form {
  background-color: rgb(255, 255, 255);
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0px 0px 15px rgba(4, 121, 0, 0.2);
  width: 600px;
  height: 500px;
  opacity: 0;
  animation: fadeIn 0.5s forwards, slideIn 0.5s forwards;
  z-index: 1;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes slideIn {
  from {
    transform: translateX(-20px);
  }
  to {
    transform: translateX(0);
  }
}
h1 {
  font-family: "YourChosenFont", sans-serif; /* Replace 'YourChosenFont' with the selected font name */
  font-size: 2rem; /* Adjust the font size as needed */
  color: #333;
  font-weight: bold; /* Make the heading bold */
}

.form-input-group {
  margin-bottom: 1.5rem;
  display: flex;
  align-items: center;
}

label {
  margin-right: 1rem;
  color: #333;
}

input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
  transition: box-shadow 0.3s ease;
}

input:hover {
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.error-message {
  color: #ff4d4d;
  background-color: #ffd1d1;
  padding: 0.5rem;
  border-radius: 5px;
  margin-bottom: 1rem;
  display: flex;
  align-items: center;
}

.error-message i {
  margin-right: 0.5rem;
  color: #ff4d4d;
}

.submit-button {
  background-color: #2ecc71;
  color: #fff;
  padding: 0.7rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #28a745;
}

p {
  margin-top: 1rem;
  text-align: center;
  color: #333;
}

router-link {
  color: #3498db;
  text-decoration: none;
}

router-link:hover {
  text-decoration: underline;
}
</style>


