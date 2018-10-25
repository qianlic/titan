import request from '../request'

const baseUrl = '/system/crawler'

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

function status (params) {
  return request(`${baseUrl}/status`, params)
}

function run (params) {
  return request(`${baseUrl}/run`, params)
}

export default {
  list,
  create,
  edit,
  remove,
  status,
  run
}
