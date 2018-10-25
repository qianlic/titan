import request from '../request'

const baseUrl = '/system/tomcat'

function list () {
  return request(`${baseUrl}/list`)
}

export default {
  list
}
