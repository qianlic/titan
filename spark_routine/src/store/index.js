import Vue from 'vue'
import Vuex from 'vuex'
import home from './modules/home'
import goods from './modules/goods'

Vue.use(Vuex)

const state = {
  address: '常熟市海虞镇福谢路徐桥公交站旁',
  workTime: '7:00-20:00',
  phoneNumber: '13962346987'
}

const getters = {
  address: state => state.address,
  workTime: state => state.workTime,
  phoneNumber: state => state.phoneNumber
}

const modules = {
  home,
  goods
}

export default new Vuex.Store({
  state,
  modules,
  getters
})
