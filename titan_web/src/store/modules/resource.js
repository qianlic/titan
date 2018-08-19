import request from '../../api/resource'

const state = {
  availablelist: [],
  availableids: [],
  availablelength: 0,
  datas: [],
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
  expandData (state, id) {
    state.datas.map(x => {
      if (x.id === id) {
        x['expand'] = x['expand'] !== true
        state.datas.filter(y => y.parentid === id).map(y => {
          y['expand'] = x['expand']
          y['display'] = x['expand']
          state.datas.filter(z => z.parentid === y.id).map(z => {
            z['display'] = x['expand']
          })
        })
      }
    })
  },
  setAvailablelist (state, availablelist) {
    state.availablelist = availablelist
    state.availableids = availablelist.map(item => item.id)
    state.availablelength = availablelist.length
  },
  setSelectRows (state, selectRows) {
    state.selectRows = selectRows
  }
}

const actions = {
  loadDatas ({state, commit}, params) {
    return request.list(params).then(response => {
      if (response.status === 0) {
        const list = response.data
        const datas = []
        list.filter(x => x.parentid === 0).forEach(x => {
          x['expand'] = true
          x['display'] = true
          datas.push(x)
          list.filter(y => y.parentid === x.id).forEach(y => {
            y['expand'] = false
            y['display'] = true
            datas.push(y)
            datas.push(...list.filter(z => z.parentid === y.id).map(x => {
              x['display'] = false
              return x
            }))
          })
        })
        commit('setDatas', datas)
      }
    })
  },
  expandData ({state, commit}, id) {
    commit('expandData', id)
  },
  loadAvailablelist ({commit}) {
    return request.availableList().then(response => {
      if (response.status === 0) {
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
  getters,
  mutations,
  actions
}
