import localStore from '../store/localStore'
import router from '../router'
import Axios from 'axios'
import { Message } from 'iview'

const request = Axios.create({
  baseURL: '/api/',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json; charset=utf-8'
  },
  timeout: 10000
})

request.interceptors.request.use(config => {
  const AUTH_TOKEN = localStore.getAuthTokenItem('Authorization')
  if (AUTH_TOKEN !== undefined) {
    config.headers.Authorization = AUTH_TOKEN
  }
  return config
})

request.interceptors.response.use(response => {
  return response.data
}, error => {
  const response = error.response
  switch (response.status) {
    case 401:
      Message.info(response.data.message)
      router.push('/login')
      break
    case 403:
      Message.info(response.data.message)
      break
  }
  return Promise.reject(error)
})

export default async function (url, params, heads) {
  Object.assign(request.defaults.headers.common, heads)
  const options = {
    method: 'POST',
    data: params
  }
  return new Promise((resolve) => {
    request(url, options)
      .then(response => resolve(response))
      .catch(error => {
        console.log('请求[' + url + ']异常', error)
      })
  })
}
