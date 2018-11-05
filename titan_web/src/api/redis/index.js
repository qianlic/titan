import request from '../request'

const baseUrl = '/monitor/redis'

function info () {
  return request(`${baseUrl}/info`)
}

export default {
  info
}
