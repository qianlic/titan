import request from '../'

const baseUrl = '/system/tomcat'

function list () {
  return request(`${baseUrl}/list`)
}

export default {
  list
}
