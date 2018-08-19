import request from '../'

const baseUrl = '/system/redis'

function info () {
  return request(`${baseUrl}/info`)
}

export default {
  info
}
