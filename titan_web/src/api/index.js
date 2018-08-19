import localStore from '../store/localStore'
import Axios from 'axios'

const request = Axios.create({
  baseURL: '/api/',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json; charset=utf-8'
  },
  validateStatus: status => {
    return status >= 200 && status <= 500
  },
  timeout: 5000
})

export default async function (url, params) {
  const AUTH_TOKEN = localStore.getAuthTokenItem('Authorization')
  if (AUTH_TOKEN !== undefined) {
    request.defaults.headers.common['Authorization'] = AUTH_TOKEN
  }
  const options = {
    method: 'POST',
    data: params
  }
  return request(url, options)
    .then(response => response.data)
    .catch(error => {
      console.log('请求[' + url + ']异常', error)
    })
}
