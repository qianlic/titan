import request from '../request'

const baseUrl = '/quartz/execute'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

export default {
  list,
  remove
}
