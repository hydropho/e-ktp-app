<template>
  <div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <h2>{{ action }} Kartu Keluarga</h2>
        <span class="close" @click="closeModal">&times;</span>
      </div>
      <div class="modal-body">
        <form action="" @submit.prevent="inputData">
          <div class="form-input">
            <label for="">Nomor Kartu Keluarga <span>*</span></label>
            <input type="number" v-model="kartuKeluarga.nomor_kk" />
            <span v-if="noKKValidation" class="validation-message"
              >Nomor kartu keluarga sudah ada!</span
            >
            <span v-if="error.nomor_kk" class="validation-message"
              >Nomor kartu keluarga harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Alamat <span>*</span></label>
            <textarea rows="3" v-model="kartuKeluarga.alamat"></textarea>
            <span v-if="error.alamat" class="validation-message"
              >Alamat harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">RT <span>*</span></label>
            <input type="number" v-model="kartuKeluarga.rt" />
            <span v-if="error.rt" class="validation-message"
              >RT harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">RW <span>*</span></label>
            <input type="number" v-model="kartuKeluarga.rw" />
            <span v-if="error.rw" class="validation-message"
              >RW harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Desa / Kelurahan <span>*</span></label>
            <input type="text" v-model="kartuKeluarga.desa_kelurahan" />
            <span v-if="error.desa_kelurahan" class="validation-message"
              >Desa / Kelurahan harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Kecamatan <span>*</span></label>
            <input type="text" v-model="kartuKeluarga.kecamatan" />
            <span v-if="error.kecamatan" class="validation-message"
              >Kecamatan harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Kabupaten / Kota <span>*</span></label>
            <input type="text" v-model="kartuKeluarga.kabupaten_kota" />
            <span v-if="error.kabupaten_kota" class="validation-message"
              >Kabupaten / Kota harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Provinsi <span>*</span></label>
            <input type="text" v-model="kartuKeluarga.provinsi" />
            <span v-if="error.provinsi" class="validation-message"
              >Provinsi harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Kode Pos <span>*</span></label>
            <input type="number" v-model="kartuKeluarga.kode_pos" />
            <span v-if="error.kode_pos" class="validation-message"
              >Kode pos harus diisi!</span
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
      kartuKeluarga: {
        nomor_kk: null,
        alamat: "",
        rt: null,
        rw: null,
        desa_kelurahan: "",
        kecamatan: "",
        kabupaten_kota: "",
        provinsi: "",
        kode_pos: null,
      },
      error: {},
      noKKValidation: false,
    };
  },
  props: ["action"],
  methods: {
    closeModal() {
      this.kartuKeluarga = {};
      document.getElementById("myModal").style.display = "none";
    },
    showMsg(status) {
      if (status === "success") {
        const data = {
          status: "success",
          header: "Berhasil !",
          body: "Kartu keluarga ditambahkan!",
          icon: "done",
        };
        this.$emit("sendToastMsg", data);
      } else {
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
    inputData() {
      this.error = {};
      this.noKKValidation = false;
      let data = this.kartuKeluarga;
      for (const property in data) {
        if (data[property] === null || data[property] === "") {
          this.error[property] = true;
        }
      }

      if (Object.keys(this.error).length === 0) {
        if (this.action === "Tambah") {
          service
            .insertKK(data)
            .then((response) => {
              if (response.status === 200) {
                this.closeModal();
                this.showMsg("success");
                this.kartuKeluarga = {};
              }
            })
            .catch((e) => {
              if (e.response.data.trace.includes("Duplicate entry")) {
                this.noKKValidation = true;
              }
              this.showMsg("danger");
              this.kartuKeluarga = data;
            });
        }
      }
    },
  },
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