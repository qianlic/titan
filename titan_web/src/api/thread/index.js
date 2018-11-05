import request from '../request'
import filedown from '../filedown'

const baseUrl = '/monitor/thread'

function list (params) {
  return request(`${baseUrl}/list`, params)
}

function download (params) {
  return filedown(`${baseUrl}/download`, params)
}

function interrupt (params) {
  return request(`${baseUrl}/interrupt`, params)
}

export default {
  list,
  download,
  interrupt
}
