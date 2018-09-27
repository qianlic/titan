import request from '../../api/image'

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
      if (response.status === 0) {
        commit('setDatas', response.data)
      }
    })
  },
  removeDatas (context, params) {
    return request.remove(params)
  },
  setSelectRows ({commit}, selectRows) {
    commit('setSelectRows', selectRows)
  }
}

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions
}
