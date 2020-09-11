import Vue from 'vue'
import iView from 'iview'
import router from './router'
import store from './store'
import './styles/index.less'

Vue.config.productionTip = false
Vue.use(iView)

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start()
  if (to.name !== 'login' && store.state.first) {
    store.commit('setFirst', false)
    next('home')
  } else {
    next()
  }
})

router.afterEach(() => {
  iView.LoadingBar.finish()
})

new Vue({
  router,
  store
}).$mount('#app')
