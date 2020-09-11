import request from '../request'

const baseUrl = '/monitor/memory'

function info () {
  return request(`${baseUrl}/info`)
}

export default {
  info
}
