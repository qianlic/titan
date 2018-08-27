import request from '../../api/tomcat'

const state = {
  datas: []
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
    return request.list().then(response => {
      if (response.status === 0) {
        commit('setDatas', response.data)
      }
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
