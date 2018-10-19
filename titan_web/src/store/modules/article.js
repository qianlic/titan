import request from '../../api/article'

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
  loadDatas ({state, commit}, params) {
    return request.list(params).then(response => {
      if (response.success) {
        commit('setDatas', response.data)
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
