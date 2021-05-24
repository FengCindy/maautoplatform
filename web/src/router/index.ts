import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/home.vue'
import About from '../views/about.vue'


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/about.vue')  //懒加载，若项目加载比较大，可使用懒加载
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
