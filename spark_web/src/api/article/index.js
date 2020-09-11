import request from '../request'

const baseUrl = '/system/article'

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

export default {
  list,
  create,
  edit,
  remove
}
