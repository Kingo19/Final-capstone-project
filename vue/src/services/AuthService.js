import axios from 'axios';

export default {

  login(user) {
    return axios.post('http://localhost:9000/login', user)
  },

  register(user) {
    return axios.post('http://localhost:9000/register', user)
  }

}
