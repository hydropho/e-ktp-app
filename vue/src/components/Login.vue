<template>
  <div class="login-content">
    <form action="" @submit.prevent="loginFunc">
      <p class="form-title">Login</p>
      <div class="form-input">
        <label for="email">Email :</label>
        <input type="email" name="email" id="email" v-model="loginData.email" />
        <span v-if="error.email" class="validation-message"
          >Email harus diisi!</span
        >
      </div>
      <div class="form-input">
        <label for="password">Password :</label>
        <input
          type="password"
          name="password"
          id="password"
          v-model="loginData.password"
        />
        <span v-if="error.password" class="validation-message"
          >Password harus diisi!</span
        >
      </div>
      <div class="form-input">
        <span v-if="loginError" class="validation-message"
          >Username / Password salah!</span
        >
        <span v-if="successMsg" class="validation-message-success"
          >Berhasil mendaftar! Silahkan login.</span
        >
      </div>
      <button type="submit" class="btn-submit">Login</button>
    </form>
    <div class="footer">
      <p>
        Don't have account?
        <span class="register" @click="$emit('registerForm')">Register</span>
      </p>
    </div>
  </div>
</template>

<script>
import service from "../services/service";
export default {
  name: "LoginForm",
  data() {
    return {
      loginData: {
        email: "",
        password: "",
      },
      error: {
        email: false,
        password: false,
      },
      loginError: false,
    };
  },
  methods: {
    loginFunc() {
      this.$emit("cleanMsg");
      this.loginError = false;
      this.error = {};
      let data = this.loginData;
      for (const property in data) {
        if (data[property] === null || data[property] === "") {
          this.error[property] = true;
        }
      }

      if (Object.keys(this.error).length === 0) {
        service
          .login(data)
          .then((response) => {
            if (response.status === 200) {
              this.$router.push("/");
            }
          })
          .catch((e) => {
            if (
              e.response.data.message.includes(
                "Incorrect result size: expected 1, actual 0"
              )
            ) {
              this.loginError = true;
            }
          });
      }
    },
  },
  props: ["successMsg"],
};
</script>

<style scoped>
.login-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 504.4px;
  padding: 1rem;
}
.form-title {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 2rem;
  text-align: center;
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1rem;
}

label {
  opacity: 0.8;
}

input {
  /* border-radius: 0.5rem; */
  padding: 0.5rem 1rem;
  border: none;
  border-bottom: 1px solid grey;
}

.btn-submit {
  text-decoration: none;
  width: 100%;
}

button {
  background-color: hsl(241.21, 40.41%, 48.04%);
  padding: 0.7rem;
  border-radius: 0.7rem;
  width: 100%;
  color: white;
  font-size: 1rem;
  border: none;
}

button:hover {
  background-color: hsl(241.21, 40.41%, 58.04%);
}

.footer {
  margin-top: 1rem;
}

.footer span {
  color: hsl(241.21, 40.41%, 48.04%);
}

.footer span:hover {
  color: hsl(241.21, 40.41%, 58.04%);
  cursor: pointer;
}

.validation-message {
  color: red;
  opacity: 0.7;
  font-size: 0.8rem;
}

.validation-message-success {
  color: green;
  opacity: 0.7;
  font-size: 0.8rem;
}
</style>