const state = {
  newGoods: [{
    'id': 1134030,
    'name': '商品一',
    'list_pic_url': '../../../static/images/1.jpg',
    'retail_price': 6
  }, {
    'id': 1134032,
    'name': '商品二',
    'list_pic_url': '../../../static/images/1.jpg',
    'retail_price': 8
  }, {
    'id': 1135002,
    'name': '商品三',
    'list_pic_url': '../../../static/images/1.jpg',
    'retail_price': 10
  }, {
    'id': 1152161,
    'name': '商品四',
    'list_pic_url': '../../../static/images/1.jpg',
    'retail_price': 15
  }]
}

const getters = {
  newGoods: state => state.newGoods
}

export default {
  namespaced: true,
  state,
  getters
}
