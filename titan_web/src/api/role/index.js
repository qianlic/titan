import request from '../request'

const baseUrl = '/system/role'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function create (params) {
  return request(`${baseUrl}/create`, params)
}

function edit (params) {
  return request(`${baseUrl}/edit`, params)
}

function status (params) {
  return request(`${baseUrl}/status`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

function availableList (params) {
  return request(`${baseUrl}/availableList`, params)
}

export default {
  list,
  create,
  edit,
  status,
  remove,
  availableList
}
