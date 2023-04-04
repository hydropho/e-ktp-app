<template>
  <div class="content">
    <div class="container">
      <div class="img-container" :class="moveImg">
        <img src="../assets/ktp.png" alt="ktp" />
      </div>
      <div class="form" :class="moveRegis">
        <Login
          @registerForm="changeRegisterForm"
          v-if="showLogin"
          :successMsg="successMsg"
          @cleanMsg="cleanMsg"
        />
        <!-- <transition name="slide" mode="in-out"> -->
        <Register
          @loginForm="changeLoginForm"
          v-if="showRegister"
          class="regis-form"
          @successRegister="successRegister"
        />
        <!-- </transition> -->
      </div>
    </div>
  </div>
</template>

<script>
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
export default {
  name: "AuthPage",
  components: {
    Login,
    Register,
  },
  data() {
    return {
      showLogin: true,
      showRegister: false,
      moveRegis: "",
      moveImg: "",
      successMsg: false,
    };
  },
  methods: {
    cleanMsg() {
      this.successMsg = false;
    },
    changeLoginForm() {
      this.showRegister = !this.showRegister;
      this.showLogin = !this.showLogin;
      this.moveRegis = "";
      this.moveImg = "";
    },
    successRegister() {
      this.showRegister = !this.showRegister;
      this.showLogin = !this.showLogin;
      this.moveRegis = "";
      this.moveImg = "";
      this.successMsg = true;
    },
    changeRegisterForm() {
      this.showLogin = !this.showLogin;
      this.showRegister = !this.showRegister;
      this.moveRegis = "move-regis";
      this.moveImg = "move-image";
    },
  },
};
</script>

<style scoped>
.content {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: hsl(241.21, 40.41%, 48.04%);
}

.container {
  background-color: hsl(0, 0%, 94.9%);
  background-color: white;
  border-radius: 1rem;
  display: grid;
  grid-template: 1fr/ 1fr 1fr;
  column-gap: 1.5rem;
  /* align-items: center; */
  padding: 2rem;
}

.img-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: hsl(241.21, 40.41%, 55%);
  border-radius: 1rem;
  transition: all 0.3s ease;
}

/* .slide-enter-active,
.slide-leave-active {
  transition: all 0.5s ease;
}

.slide-enter,
.slide-leave-to {
  opacity: 0;
  transform: translateX(0);
} */

.move-regis {
  transform: translateX(calc(-100% - 1.5rem));
}

.move-image {
  transform: translateX(calc(100% + 1.5rem));
}
</style>