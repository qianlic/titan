import request from '../request'

const baseUrl = '/quartz/jobDetail'

function availableList (params) {
  return request(`${baseUrl}/all`, params)
}

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

function run (params) {
  return request(`${baseUrl}/run`, params)
}

function pause (params) {
  return request(`${baseUrl}/pause`, params)
}

function resume (params) {
  return request(`${baseUrl}/resume`, params)
}

export default {
  availableList,
  list,
  create,
  edit,
  remove,
  run,
  pause,
  resume
}
