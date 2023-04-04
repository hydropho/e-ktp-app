import Vue from 'vue'
import App from './App.vue'
import Routes from './routes.js'
import VueRouter from 'vue-router'
import VueSweetalert2 from 'vue-sweetalert2';

Vue.use(VueSweetalert2)
Vue.config.productionTip = false
Vue.use(VueRouter)

const router = new VueRouter({
  routes: Routes,
  mode: 'history',
});

router.replace({ path: '/auth' })


new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
