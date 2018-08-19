import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index'
import NotFound from '../views/404'
import Login from '../views/login/Login'

// 模块
import system from './system'
import monitor from './monitor'
import quartz from './quartz'
import crawler from './crawler'

Vue.use(VueRouter)

// 路由配置
const RouterConfig = {
  mode: 'history',
  routes: [{
    path: '/',
    name: 'index',
    component: Index,
    children: [{
      path: '',
      redirect: '/home'
    }, {
      path: 'home',
      name: 'home',
      component: () => import('../views/home/Home')
    },
    ...system,
    ...monitor,
    ...quartz,
    ...crawler]
  }, {
    path: '/login',
    name: 'login',
    component: Login
  }, {
    path: '*',
    name: '*',
    component: NotFound
  }]
}

export default new VueRouter(RouterConfig)
