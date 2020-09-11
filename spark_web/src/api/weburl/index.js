import request from '../request'

const baseUrl = '/crawler/webUrl'

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
