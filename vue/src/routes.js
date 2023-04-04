// Parent
import Dashboard from './containers/Dashboard'
import Auth from './pages/Auth'

// Child
import Home from './pages/Home.vue'
import KartuKeluarga from './pages/KartuKeluarga.vue'
import Users from './pages/Users.vue'

export default [
    {
        path: '/',
        name: '',
        redirect: 'home',
        component: Dashboard,
        children: [
            {
                path: '/',
                name: 'home',
                component: Home,
            },
            {
                path: '/kartu-keluarga',
                name: 'kartu-keluarga',
                component: KartuKeluarga
            },
            {
                path: '/users',
                name: 'users',
                component: Users
            },
        ]
    },
    {
        path: "/auth",
        name: "auth",
        component: Auth,
    },
]