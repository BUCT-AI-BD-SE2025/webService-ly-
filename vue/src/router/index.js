import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

import LoginView from '../views/LoginView.vue'
import Register from '../views/Register.vue'
import Browse from '../views/Browse.vue'
import profile from '../views/profile.vue'
import forgetPassword from '../views/forget-password.vue'

import  ArtifactDetail from '../views/ArtifactDetail.vue'
import  visualization from '../views/visualization.vue'
 import ChangePassword from '../views/ChangePassword.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path:'/register',
      name:'register',
      component:Register,
    },
    {
      path:'/Browse',
      name:'Browse',
      component:Browse,
    },
    {
      path: '/forget-password',
      name: 'forget-password',
      component: forgetPassword,
    },
    {
      path: '/profile',
      name: 'profile',
      component: profile,
    },
    {
      path: '/artifact',
      name: 'ArtifactDetail',
      component: () => import('@/views/ArtifactDetail.vue')
    },

    {
      path: '/ChangePassword.vue',
      name: 'ChangePassword',
      component: ChangePassword
    },
    {
      path:'/visualization.vue',
      name: 'visualization',
      component:visualization
    }


  ],
})

export default router
