import localStore from '../store/localStore'
import router from '../router'
import Axios from 'axios'
import {Message} from 'iview'

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

export default async function (url, params, heads) {
  Object.assign(request.defaults.headers.common, heads)
  const options = {
    method: 'POST',
    data: params
  }
  return new Promise((resolve, reject) => {
    request(url, options)
      .then(response => {
        const data = response.data
        if (data.success) {
          resolve(data)
        } else {
          Message.warning(data.message)
          reject(data)
        }
      }).catch(error => {
        const {status, data} = error.response
        if (data.message) Message.error(data.message)
        switch (status) {
          case 401:router.push('/login')
        }
        console.log('请求[' + url + ']异常', data)
      })
  })
}
