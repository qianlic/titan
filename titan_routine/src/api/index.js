const BASE_URL = 'https://api.weixin.qq.com/'

const header = {
  'content-type': 'application/json' // 默认值
}

export default async function (url, params, args) {
  return new Promise((resolve, reject) => {
    wx.request({
      url: BASE_URL + url,
      data: params,
      header,
      ...args,
      success: function (response) {
        resolve(response.data)
      },
      fail: function (error) {
        reject(error)
      }
    })
  })
}
