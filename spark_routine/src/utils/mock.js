const BASE_URL = 'https://www.easy-mock.com/mock/5b84b51f8ce53c42325dd89f/titan_routine/'
const header = {
  'content-type': 'application/json' // 默认值
}

export default async function (url, params) {
  return new Promise((resolve, reject) => {
    wx.request({
      url: BASE_URL + url,
      data: params,
      header,
      method: 'POST',
      success: function (response) {
        resolve(response.data)
      },
      fail: function (error) {
        reject(error)
      }
    })
  })
}
