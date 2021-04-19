<template>
  <b-container>
    <b-jumbotron class="text-center mb-0">
      <b-container>
        <h1 class="jumbotron-heading">
            Welcome! HipsterShop
        </h1>
      </b-container>
    </b-jumbotron>
    <br><br>
    <b-row class="justify-content-md-center">
      <b-col cols="7">
        <b-card style="max-width: 40rem;" title="Login">
          <b-input
              id="input-1"
              v-model="email"
              required
              placeholder="Enter UserEmail"></b-input>
          <br>
          <b-input
              id="input-2"
              v-model="password"
              type="password"
              required
              placeholder="Enter Password"></b-input>
        </b-card>
      </b-col>
    </b-row>
    <br>
    <b-row class="justify-content-md-center">
      <b-col cols="1">
        <b-button variant="primary" @click="login()">Login</b-button>
      </b-col>
      <b-col cols="1">
        <b-button variant="danger" @click="onReset()" class="ml-2">Reset</b-button>
      </b-col>

    </b-row>


  </b-container>
</template>

<script>
import {loginHelper} from "@/helper/LoginHelper";
import {mapMutations} from "vuex";

export default {
  name: 'Login',
  components: {
  },
  mounted:function(){
    const token = localStorage.getItem('token');
    if(token === null || token === ''){
      localStorage.removeItem('token');
      this.setIsLogin(false);
      this.$router.push('/login');
    }else { // Logout 처리
      localStorage.removeItem('token');
      this.setIsLogin(false);
      this.$router.push('/login');
    }
  },
  methods: {
    login: async function (){
      const user = await loginHelper.login(this.email, this.password);
      if(user) {
        this.setIsLogin(true);
        await this.$router.push('/home');
      }else {
        alert('Login Failed! ');
      }
    },
    onReset: function (){
      this.email = '';
      this.password = '';
    },
    ...mapMutations('common', ['setIsLogin']),
  },
  data: function () {
    return {
      email: '',
      password: '',
    }
  },
  computed: {

  }
}
</script>
