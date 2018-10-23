import request from '../../api/resource'
import {formatTree} from '../../utils'

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
    state.datas.forEach(x => {
      if (x.id === id) {
        const s = !x['expand']
        x['expand'] = s
        state.datas.filter(y => y.parentid === id).forEach(y => {
          y['display'] = s
          if (!s) {
            y['expand'] = s
            state.datas.filter(z => z.parentid === y.id).forEach(z => {
              z['display'] = s
            })
          }
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
  },
  removeDatas (state, {ids}) {
    state.datas = state.datas.filter(x => !ids.includes(x.id))
  }
}

const actions = {
  loadDatas ({state, commit}, params) {
    return request.list(params).then(response => {
      const list = response.data
      const datas = formatTree(list)
      datas.forEach(x => {
        x['expand'] = false
        x['display'] = false
        if (x.level === 1) {
          x['display'] = true
        }
      })
      commit('setDatas', datas)
    })
  },
  expandData ({state, commit}, id) {
    commit('expandData', id)
  },
  loadAvailablelist ({commit}) {
    return request.availableList().then(response => commit('setAvailablelist', response.data))
  },
  removeDatas ({commit}, params) {
    commit('removeDatas', params)
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
