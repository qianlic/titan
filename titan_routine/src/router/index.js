module.exports = [{
  path: 'pages/index',
  name: 'counter'
}, {
  path: 'pages/home',
  name: 'counter'
}, {
  path: 'pages/counter',
  name: 'counter'
}, {
  path: 'plugin/logs',
  subPackage: true,
  config: { // 页面配置，即 page.json 的内容
    navigationBarTitleText: '查看启动日志'
  }
}
]
