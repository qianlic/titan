import request from '../../api/redis'

const state = {
  datas: {}
}

const getters = {
  datas: state => state.datas
}

const mutations = {
  setDatas (state, datas) {
    state.datas = datas
  }
}

const actions = {
  loadDatas ({commit}) {
    return request.info().then(response => commit('setDatas', response.data))
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  getters,
  actions
}
