import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import role from './modules/role'
import resource from './modules/resource'
import image from './modules/image'
import thread from './modules/thread'
import memory from './modules/memory'
import redis from './modules/redis'
import tomcat from './modules/tomcat'
import jobdetail from './modules/jobdetail'
import trigger from './modules/trigger'
import crawler from './modules/crawler'
import weburl from './modules/weburl'
import createLogger from '../plugin/logger'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

const state = {
  first: true,
  token: {
    roles: [],
    resources: []
  },
  cachepages: [{
    code: 'home',
    name: '主页',
    color: 'green'
  }]
}

const mutations = {
  setFirst (state, first) {
    state.first = first
  },
  setTokenInfo (state, token) {
    state.token = token
  },
  addCachePage (state, page) {
    state.cachepages.forEach(page => {
      if (page.color === 'green') {
        page.color = 'yellow'
      }
    })
    state.cachepages.push(page)
  },
  delCachePage (state, code) {
    const idx = state.cachepages.findIndex(page => page.code === code)
    state.cachepages.splice(idx, 1)
    if (!state.cachepages.some(page => page.color === 'green')) {
      state.cachepages[state.cachepages.length - 1].color = 'green'
    }
  },
  actCachePage (state, code) {
    state.cachepages.forEach(page => {
      if (page.code === code) {
        page.color = 'green'
      } else if (page.color === 'green') {
        page.color = 'yellow'
      }
    })
  }
}

const getters = {
  first: state => state.first,
  usercode: state => state.token.usercode,
  username: state => state.token.username,
  roles: state => state.token.roles,
  resources: state => state.token.resources,
  cachepages: state => state.cachepages,
  activepage: state => state.cachepages.find(page => page.color === 'green')
}

const actions = {
  loadTokenInfo ({commit}, token) {
    commit('setTokenInfo', token)
  },
  openPageInfo ({state, commit}, code) {
    if (!state.cachepages.some(page => page.code === code)) {
      const resource = state.token.resources.find(resource => resource.resourcecode === code)
      commit('addCachePage', {
        code: resource.resourcecode,
        name: resource.resourcename,
        color: 'green'
      })
    } else {
      commit('actCachePage', code)
    }
  },
  closePageInfo ({state, commit}, code) {
    commit('delCachePage', code)
  }
}

const modules = {
  user,
  role,
  resource,
  image,
  thread,
  memory,
  redis,
  tomcat,
  jobdetail,
  trigger,
  crawler,
  weburl
}

export default new Vuex.Store({
  state,
  modules,
  mutations,
  getters,
  actions,
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
