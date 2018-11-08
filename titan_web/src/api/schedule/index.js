import request from '../request'

const baseUrl = '/quartz/schedule'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function create (params) {
  return request(`${baseUrl}/create`, params)
}

function edit (params) {
  return request(`${baseUrl}/edit`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

function start (params) {
  return request(`${baseUrl}/start`, params)
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
  edit,
  remove,
  start,
  pause,
  resume
}
