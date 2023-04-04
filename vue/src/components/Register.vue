<template>
  <div class="register-content">
    <form action="" @submit.prevent="registerFunc">
      <p class="form-title">Register</p>
      <div class="form-input">
        <label for="name">Nama :</label>
        <input type="text" name="name" id="name" v-model="registerData.nama" />
        <span v-if="error.nama" class="validation-message"
          >Nama harus diisi!</span
        >
      </div>
      <div class="form-input">
        <label for="usernameRegister">Email :</label>
        <input
          type="email"
          name="username"
          id="usernameRegister"
          v-model="registerData.email"
        />
        <span v-if="error.email" class="validation-message"
          >Email harus diisi!</span
        >
        <span v-if="error.emailUsed" class="validation-message"
          >Email sudah digunakan!</span
        >
      </div>
      <div class="form-input">
        <label for="passwordRegister">Password :</label>
        <input
          type="password"
          name="password"
          id="passwordRegister"
          v-model="registerData.password"
        />
        <span v-if="error.password" class="validation-message"
          >Password harus diisi!</span
        >
      </div>
      <div class="form-input">
        <label for="password2">Konfirmasi Password :</label>
        <input
          type="password"
          name="password2"
          id="password2"
          v-model="password2"
        />
        <span v-if="error.password2" class="validation-message"
          >Konfirmasi Password harus diisi!</span
        >
        <span v-if="error.notSame" class="validation-message"
          >Konfirmasi Password salah!</span
        >
      </div>

      <!-- <router-link class="btn-submit" to="/"> -->
      <button type="submit">Register</button>
      <!-- </router-link> -->
    </form>
    <div class="footer">
      <p>
        Already have account?
        <span class="register" @click="$emit('loginForm')">Login</span>
      </p>
    </div>
  </div>
</template>

<script>
import service from "../services/service";

export default {
  name: "RegisterForm",
  data() {
    return {
      registerData: {
        nama: "",
        email: "",
        password: "",
      },
      password2: "",
      error: {
        nama: false,
        email: false,
        password: false,
        password2: false,
        notSame: false,
        emailUsed: false,
      },
      arrEmail: [],
    };
  },
  methods: {
    getAllData() {
      service
        .getAllUser()
        .then((response) => {
          this.arrEmail = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    registerFunc() {
      this.emailUsed = false;
      this.error.notSame = false;
      this.error = {};
      let data = this.registerData;

      if (this.password2 === "") {
        this.error.password2 = true;
      }

      this.getAllData();

      for (let i = 0; i < this.arrEmail.length; i++) {
        if (this.arrEmail[i].email == this.registerData.email) {
          this.error.emailUsed = true;
        }
      }

      for (const property in data) {
        if (data[property] === "") {
          this.error[property] = true;
        }
      }

      if (this.password2 != data.password) {
        this.error.notSame = true;
      }

      if (Object.keys(this.error).length === 0) {
        service
          .register(data)
          .then((response) => {
            if (response.status === 200) {
              this.$emit("successRegister");
            }
          })
          .catch((e) => {
            if (e.response.data.trace.includes("Duplicate entry")) {
              this.emailUsed = true;
            }
          });
      }
    },
  },
  mounted() {
    this.getAllData();
  },
};
</script>

<style scoped>
.register-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 1rem;
}

.form-title {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
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

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus,
input:-webkit-autofill:active {
  transition: background-color 5000s ease-in-out 0s;
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
  margin-top: 1.5rem;
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
</style>
