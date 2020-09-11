import request from '../'

function wxacode (params) {
  return request(`wxa/getwxacodeunlimit?access_token=` + params.access_token, {
    path: params.path,
    scene: 'titan_routine'
  }, {
    method: 'POST',
    responseType: 'arraybuffer'
  })
}

export default {
  wxacode
}
