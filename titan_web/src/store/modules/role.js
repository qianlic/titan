import request from '../../api/role'

const state = {
  availablelist: [],
  availableids: [],
  availablelength: 0,
  datas: {},
  selectRows: []
}

const getters = {
  availablelist: state => state.availablelist,
  availableids: state => state.availableids,
  availablelength: state => state.availablelength,
  datas: state => state.datas,
  selectRows: state => state.selectRows,
  selectIds: state => state.selectRows.map(item => item.id)
}

const mutations = {
  setDatas (state, datas) {
    state.datas = datas
  },
  setAvailablelist (state, availablelist) {
    if (availablelist) {
      state.availablelist = availablelist
      state.availableids = availablelist.map(item => item.id)
      state.availablelength = availablelist.length
    }
  },
  setSelectRows (state, selectRows) {
    state.selectRows = selectRows
  }
}

const actions = {
  loadDatas ({state, commit}, params) {
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
