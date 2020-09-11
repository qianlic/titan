import Bmob from '@/utils/bmob'

const state = {
  banner: []
}

const getters = {
  banner: state => state.banner
}

const mutations = {
  setBanner (state, banner) {
    state.banner = banner
  }
}

const actions = {
  loadDatas ({commit}) {
    Bmob.Query('banner').find().then(response => {
      commit('setBanner', response)
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
