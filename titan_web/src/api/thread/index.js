import request from '../'

const baseUrl = '/system/thread'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function interrupt (params) {
  return request(`${baseUrl}/interrupt`, params)
}

export default {
  list,
  interrupt
}
