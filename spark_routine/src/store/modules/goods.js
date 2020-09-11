import Bmob from '@/utils/bmob'

const state = {
  goods: []
}

const getters = {
  goods: state => state.goods
}

const mutations = {
  setGoods (state, goods) {
    state.goods = goods
  }
}

const actions = {
  loadDatas ({commit}) {
    Bmob.Query('goods').find().then(response => {
      commit('setGoods', response)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  getters,
  actions
}
