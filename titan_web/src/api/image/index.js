import request from '../'

const baseUrl = '/system/image'

function upload (params) {
  return request(`${baseUrl}/upload`, params)
}

function remove (params) {
  return request(`${baseUrl}/remove`, params)
}

function list (params) {
  return request(`${baseUrl}/list`, params)
}

export default {
  upload,
  remove,
  list
}
