import request from '../'

const baseUrl = '/system/user'

function token (params) {
  return request(`${baseUrl}/token`, params)
}

function login (params) {
  return request(`${baseUrl}/login`, params)
}

function logout (params) {
  return request(`${baseUrl}/logout`, params)
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

function status (params) {
  return request(`${baseUrl}/status`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

function password (params) {
  return request(`${baseUrl}/password`, params)
}

export default {
  token,
  login,
  logout,
  list,
  create,
  edit,
  status,
  remove,
  password
}
