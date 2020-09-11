import request from '../request'

const baseUrl = '/monitor/tomcat'

function list () {
  return request(`${baseUrl}/list`)
}

export default {
  list
}
