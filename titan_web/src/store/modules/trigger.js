import request from '../../api/trigger'

const state = {
  datas: {},
  selectRows: []
}

const getters = {
  datas: state => state.datas,
  selectRows: state => state.selectRows
}

const mutations = {
  setDatas (state, datas) {
    state.datas = datas
  },
  setSelectRows (state, selectRows) {
    state.selectRows = selectRows
  }
}

const actions = {
  loadDatas ({commit}, params) {
    return request.list(params).then(response => commit('setDatas', response.data))
  },
  createData (context, params) {
    return request.create(params)
  },
  removeDatas (context, params) {
    return request.remove(params)
  },
  pauseTigger (context, params) {
    return request.pause(params)
  },
  resumeTigger (context, params) {
    return request.resume(params)
  },
  setSelectRows ({commit}, selectRows) {
    commit('setSelectRows', selectRows)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  getters,
  actions
}
