import request from '../../api/user'

const state = {
  datas: {},
  selectRows: []
}

const getters = {
  datas: state => state.datas,
  selectRows: state => state.selectRows,
  selectIds: state => state.selectRows.map(item => item.id)
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
  downloadDatas ({commit}, params) {
    return request.download(params)
  },
  removeDatas (context, params) {
    return request.remove(params)
  },
  createData (context, params) {
    return request.create(params)
  },
  editDatas (context, params) {
    return request.edit(params)
  },
  editStatus (context, params) {
    return request.status(params)
  },
  editPassword (context, params) {
    return request.password(params)
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
