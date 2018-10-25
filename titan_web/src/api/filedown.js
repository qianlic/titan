import localStore from '../store/localStore'
import router from '../router'
import Axios from 'axios'
import {Message} from 'iview'

function download (filename = 'download.xls', blob) {
  const link = document.createElement('a')
  if ('download' in link) {
    link.href = window.URL.createObjectURL(blob)
    link.download = filename
    document.body.appendChild(link)
    link.click()
    window.URL.revokeObjectURL(link.href)
    document.body.removeChild(link)
  } else { // IE10+下载
    navigator.msSaveBlob(blob, filename)
  }
}

const request = Axios.create({
  baseURL: '/api/',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json; charset=utf-8'
  },
  responseType: 'arraybuffer',
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
        const disposition = response.headers['content-disposition']
        if (disposition) {
          download(params.filename, new Blob([data]))
        } else {
          const enc = new TextDecoder('utf-8')
          const res = JSON.parse(enc.decode(new Uint8Array(data)))
          Message.warning(res.message)
          reject(res)
        }
      }).catch(error => {
        const {status, data} = error.response
        if (data.message) Message.error(data.message)
        switch (status) {
          case 401:
            router.push('/login')
        }
        console.log('请求[' + url + ']异常', data)
      })
  })
}
