module.exports = [{
  path: 'pages/home',
  name: 'home',
  config: {
    usingComponents: {
      'i-cell-group': '../../static/ivew/cell-group/index',
      'i-cell': '../../static/ivew/cell/index',
      'i-icon': '../../static/ivew/icon/index',
      'i-notice-bar': '../../static/ivew/notice-bar/index',
      'i-tab-bar': '../../static/ivew/tab-bar/index',
      'i-tab-bar-item': '../../static/ivew/tab-bar-item/index'
    }
  }
}, {
  path: 'pages/goods',
  name: 'goods',
  config: {
    usingComponents: {
      'i-tab-bar': '../../static/ivew/tab-bar/index',
      'i-tab-bar-item': '../../static/ivew/tab-bar-item/index'
    }
  }
}, {
  path: 'pages/shared',
  name: 'shared',
  config: {
    usingComponents: {
      'i-tab-bar': '../../static/ivew/tab-bar/index',
      'i-tab-bar-item': '../../static/ivew/tab-bar-item/index'
    }
  }
}]
