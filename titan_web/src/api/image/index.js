import request from '../'
import axios from 'axios'

const baseUrl = '/system/image'

function upload (params) {
  let config = {
    headers: {'Content-Type': 'multipart/form-data'}
  }
  return axios.post(`/api/${baseUrl}/upload`, params, config)
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
