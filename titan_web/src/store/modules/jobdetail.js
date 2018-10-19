import request from '../../api/jobdetail'

const state = {
  availablelist: [],
  datas: {},
  selectRows: []
}

const getters = {
  availablelist: state => state.availablelist,
  datas: state => state.datas,
  selectRows: state => state.selectRows
}

const mutations = {
  setDatas (state, datas) {
    state.datas = datas
  },
  setAvailablelist (state, availablelist) {
    state.availablelist = availablelist
  },
  setSelectRows (state, selectRows) {
    state.selectRows = selectRows
  }
}

const actions = {
  loadDatas ({commit}, params) {
    return request.list(params).then(response => {
      if (response.success) {
        commit('setDatas', response.data)
      }
    })
  },
  loadAvailablelist ({commit}) {
    return request.availableList().then(response => {
      if (response.success) {
        commit('setAvailablelist', response.data)
      }
    })
  },
  createData (context, params) {
    return request.create(params)
  },
  removeDatas (context, params) {
    return request.remove(params)
  },
  editDatas (context, params) {
    return request.edit(params)
  },
  runJob (context, params) {
    return request.run(params)
  },
  pauseJob (context, params) {
    return request.pause(params)
  },
  resumeJob (context, params) {
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
