import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView";
import RegisterView from "@/views/RegisterView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    // redirect: '/login'
    // redirect: '/mainPage'
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/index',
    name: 'index', component: () => import("../views/IndexView.vue")
  },
  {
    path: '/mainPage',
    name: "mainPage", component: () => import("../views/MainPageView")
  },
  {
    path: '/lottery',
    name: "lottery", component: () => import("../views/LotteryView")
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
