import request from '../'

function token () {
  return request(`cgi-bin/token`, {
    grant_type: 'client_credential',
    appid: 'wxbfc62b6049fd5861',
    secret: '07d155e061d2788fe97b8421d80ba11b'
  }, {
    method: 'GET'
  })
}

export default {
  token
}
