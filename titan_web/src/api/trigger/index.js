import request from '../request'

const baseUrl = '/system/trigger'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function create (params) {
  return request(`${baseUrl}/create`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

function pause (params) {
  return request(`${baseUrl}/pause`, params)
}

function resume (params) {
  return request(`${baseUrl}/resume`, params)
}

export default {
  list,
  create,
  remove,
  pause,
  resume
}
