import request from '../'

const baseUrl = '/system/memory'

function info () {
  return request(`${baseUrl}/info`)
}

export default {
  info
}
