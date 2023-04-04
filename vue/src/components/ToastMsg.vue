<template>
  <div id="snackbar">
    <div class="toast-container" :class="setToastMsg.status">
      <span class="material-symbols-outlined">
        {{ setToastMsg.icon }}
      </span>
      <div class="msg">
        <p>{{ setToastMsg.header }}</p>
        <p>{{ setToastMsg.body }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      setToastMsg: {},
    };
  },
  props: ["toastMsg"],
  watch: {
    toastMsg(newVal) {
      this.setToastMsg = newVal;
    },
  },
};
</script>

<style lang="scss" scoped>
.toast-container {
  min-width: 100%;
  //   background-color: red;
  padding: 1rem;
  padding-right: 1.5rem;
  border-radius: 1rem;
  //   border: 1px solid #ddd;
  //   width: 300px;
  font-size: 1rem;
  color: white;
  display: grid;
  grid-template: 1fr / 1fr 3fr;
  align-items: center;

  &.success {
    background-color: hsl(157.95, 95.4%, 34.12%);
  }

  &.danger {
    background-color: salmon;
  }
}

#snackbar {
  visibility: hidden;
  position: fixed;
  z-index: 100;
  top: 2rem;
  right: 1rem;
  margin-right: 2rem;
  min-width: 250px;

  &.show {
    visibility: visible;
    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
    animation: fadein 0.5s, fadeout 0.5s 2.5s;
  }

  span {
    font-size: 4rem;
    font-weight: 600;
    margin-right: 0.2rem;
  }

  .msg {
    p:first-child {
      font-weight: 600;
    }
  }
}

@-webkit-keyframes fadein {
  from {
    top: 0;
    opacity: 0;
  }
  to {
    top: 2rem;
    opacity: 1;
  }
}

@keyframes fadein {
  from {
    top: 0;
    opacity: 0;
  }
  to {
    top: 2rem;
    opacity: 1;
  }
}

@-webkit-keyframes fadeout {
  from {
    top: 2rem;
    opacity: 1;
  }
  to {
    top: 0;
    opacity: 0;
  }
}

@keyframes fadeout {
  from {
    top: 2rem;
    opacity: 1;
  }
  to {
    top: 0;
    opacity: 0;
  }
}
</style>