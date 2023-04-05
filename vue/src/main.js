import { createApp } from 'vue'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
// import VueSweetalert2 from 'vue-sweetalert2'
import Routes from './routes.js'

// Vue.use(VueSweetalert2)
// Vue.config.productionTip = false
// Vue.use(VueRouter)

const router = createRouter({
    routes: Routes,
    history: createWebHistory(),
});

router.replace({ redirect: 'auth' })
// router.push({ path: '/auth' })


createApp(App).use(router).mount('#app')
