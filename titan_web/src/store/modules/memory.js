import request from '../../api/memory'

const state = {
  memoryUsed: [],
  fhysicalUsed: [],
  swapUsed: [],
  memoryDetails: undefined
}

const getters = {
  memoryUsed: state => state.memoryUsed,
  fhysicalUsed: state => state.fhysicalUsed,
  swapUsed: state => state.swapUsed,
  memoryDetails: state => state.memoryDetails
}

const mutations = {
  setDatas (state, datas) {
    state.memoryUsed = [{
      name: 'nonHeap',
      value: datas.usedNonHeapMemory
    }, {
      name: 'heap',
      value: datas.usedMemory - datas.usedNonHeapMemory
    }, {
      name: 'free',
      value: datas.freeMemory
    }, {
      name: 'extensible',
      value: datas.maxMemory - datas.totalMemory
    }]
    state.fhysicalUsed = [{
      name: 'used',
      value: datas.totalPhysicalMemorySize - datas.freePhysicalMemorySize
    }, {
      name: 'free',
      value: datas.freePhysicalMemorySize
    }]
    state.swapUsed = [{
      name: 'used',
      value: datas.totalSwapSpaceSize - datas.freeSwapSpaceSize
    }, {
      name: 'free',
      value: datas.freeSwapSpaceSize
    }]
    state.memoryDetails = datas.memoryDetails
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
