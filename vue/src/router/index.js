import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

import LoginView from '../views/LoginView.vue'
import Register from '../views/Register.vue'
import Browse from '../views/Browse.vue'
import profile from '../views/profile.vue'
import forgetPassword from '../views/forget-password.vue'
import  ArtifactDetail from '../views/ArtifactDetail.vue'
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
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
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
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: profile,
    },
    {
      path: '/artifact/:id',
      name: 'ArtifactDetail',
      component: ArtifactDetail
    },
  ],
})

export default router
