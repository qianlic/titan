import Vue from 'vue'
import App from '@/App'
import store from '@/store'
import MpvueRouterPatch from 'mpvue-router-patch'

Vue.config.productionTip = false
Vue.use(MpvueRouterPatch)
const app = new Vue({
  store,
  ...App
})
app.$mount()

export default {
  config: {
    window: {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: '我的小程序',
      navigationBarTextStyle: 'black'
    },
    tabBar: {
      backgroundColor: '#fafafa',
      borderStyle: 'white',
      selectedColor: '#b4282d',
      color: '#666',
      list: [{
        pagePath: 'pages/index',
        iconPath: 'static/images/ic_menu_choice_nor.png',
        selectedIconPath: 'static/images/ic_menu_choice_pressed.png',
        text: '首页'
      }, {
        pagePath: 'pages/home',
        iconPath: 'static/images/ic_menu_topic_nor.png',
        selectedIconPath: 'static/images/ic_menu_topic_pressed.png',
        text: '我的'
      }]
    }
  }
}
