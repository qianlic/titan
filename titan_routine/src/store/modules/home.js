const state = {
  banner: [{
    'id': 1,
    'name': '活动一',
    'image_url': '../../../static/images/2.jpg'
  }, {
    'id': 2,
    'name': '活动二',
    'image_url': '../../../static/images/2.jpg'
  }, {
    'id': 3,
    'name': '活动三',
    'image_url': '../../../static/images/2.jpg'
  }]
}

const getters = {
  banner: state => state.banner
}

export default {
  namespaced: true,
  state,
  getters
}
