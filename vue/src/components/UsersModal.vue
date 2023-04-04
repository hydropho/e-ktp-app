<template>
  <div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <h2>{{ action }} User</h2>
        <span class="close" @click="closeModal">&times;</span>
      </div>
      <div class="modal-body">
        <form action="" @submit.prevent="addUser">
          <div class="form-input">
            <label for="">Nama <span>*</span></label>
            <input type="text" v-model="user.nama" />
            <span v-if="error.nama" class="validation-message"
              >Nama harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Email <span>*</span></label>
            <input type="email" v-model="user.email" />
            <span v-if="error.email" class="validation-message"
              >Email harus diisi!</span
            >
            <span v-if="emailValidation" class="validation-message"
              >Email sudah digunakan!</span
            >
          </div>
          <div class="form-input">
            <label for="">Password <span>*</span></label>
            <input type="password" v-model="user.password" />
            <span v-if="error.password" class="validation-message"
              >Password harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Konfirmasi password <span>*</span></label>
            <input type="password" v-model="password2" />
            <span v-if="error.password2" class="validation-message"
              >Konfirmasi Password harus diisi!</span
            >
            <span v-if="notSame" class="validation-message"
              >Konfirmasi password harus sama!</span
            >
          </div>
          <div class="btn-input">
            <button type="reset" class="btn-reset">Reset</button>
            <button type="submit" class="btn-submit">Submit</button>
          </div>
        </form>
      </div>
      <!-- <div class="modal-footer">
            <h3>Modal Footer</h3>
        </div> -->
    </div>
  </div>
</template>

<script>
import service from "../services/service.js";
export default {
  data() {
    return {
      password2: "",
      user: {
        nama: "",
        email: "",
        password: "",
      },
      error: {
        nama: false,
        email: false,
        password: false,
        password2: false,
      },
      notSame: false,
      emailValidation: false,
    };
  },
  methods: {
    closeModal() {
      this.user = {};
      this.password2 = "";
      this.$emit("resetAction");
      document.getElementById("myModal").style.display = "none";
    },
    showMsg(status) {
      if (status === "success") {
        const data = {
          status: "success",
          header: "Berhasil !",
          body: "User ditambahkan!",
          icon: "done",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "gagal") {
        const data = {
          status: "danger",
          header: "Gagal !",
          body: "Terdapat kesalahan!",
          icon: "close",
        };
        this.$emit("sendToastMsg", data);
      }

      var x = document.getElementById("snackbar");
      x.className = "show";
      setTimeout(function () {
        x.className = x.className.replace("show", "");
      }, 3000);
    },
    addUser() {
      let data = this.user;
      let password2 = this.password2;
      this.error = {};
      this.notSame = false;
      this.emailValidation = false;

      if (this.password2 === "") {
        this.error.password2 = true;
      }

      for (const property in data) {
        if (data[property] === null || data[property] === "") {
          this.error[property] = true;
        }
      }

      if (Object.keys(this.error).length === 0) {
        if (data.password == password2) {
          service
            .register(data)
            .then((response) => {
              if (response.status === 200) {
                this.showMsg("success");
                this.closeModal();
              }
            })
            .catch((e) => {
              if (e.response.data.trace.includes("Duplicate entry")) {
                this.emailValidation = true;
              }
              this.user = data;
              this.password2 = password2;
              this.showMsg("gagal");
            });
        } else {
          this.notSame = true;
        }
      }
    },
  },
  props: ["action"],
};
</script>

<style lang="scss" scoped>
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 1rem; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  border-radius: 1rem;
  background-color: white;
  margin: auto;
  padding: 2rem;
  width: 50%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

.modal-header {
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* The Close Button */
.close {
  color: black;
  font-size: 2rem;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: salmon;
  text-decoration: none;
  cursor: pointer;
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1rem;
  color: hsl(221.74, 9.62%, 46.86%);

  textarea {
    width: 100%;
    padding: 0.7rem 1rem;
    border-radius: 0.7rem;
    border: 1px solid #ddd;
    margin-top: 0.5rem;
  }

  input {
    width: 100%;
    padding: 0.7rem 1rem;
    border-radius: 0.7rem;
    border: 1px solid #ddd;
    margin-top: 0.5rem;

    /* Chrome, Safari, Edge, Opera */
    &::-webkit-outer-spin-button,
    &::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }
  }
  .validation-message {
    color: red;
    opacity: 0.7;
    font-size: 0.8rem;
  }

  label {
    span {
      color: red;
      opacity: 0.7;
    }
  }
}

.btn-input {
  display: flex;
  justify-content: flex-end;

  button {
    background: none;
    border: none;
    padding: 0.6rem 1rem;
    border-radius: 0.6rem;
    color: white;
    cursor: pointer;

    &.btn-reset {
      background-color: salmon;
      margin-right: 0.5rem;
    }

    &.btn-submit {
      background-color: hsl(241.21, 40.41%, 48.04%);
    }
  }
}
</style>