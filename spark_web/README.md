## 项目创建
- 安装vue-cli脚手架构建工具
```
npm install vue-cli -g
```
- 初始化项目结构框架
```
vue init webpack titan_web 
```
- 安装vuex状态管理
```
npm install vuex --save
npm install axios --save // 交互工具
npm install storejs // 安装本地存储
```
- 安装iview UI 组件库
```
npm install iview --save
npm install babel-plugin-import --save-dev
npm install --save-dev less-loader less
```
## 项目初始化
- 安装项目所需的依赖
```
npm install
```
- 热加载方式运行项目
```
npm run dev
```
- 项目结构
```
├── build                      // 构建相关  
├── config                     // 配置相关
├── src                        // 源代码
│   ├── api                    // 所有请求
│   ├── assets                 // 主题 字体等静态资源
│   ├── components             // 全局公用组件
│   ├── directive              // 全局指令
│   ├── filtres                // 全局 filter
│   ├── icons                  // 项目所有 svg icons
│   ├── lang                   // 国际化 language
│   ├── mock                   // 项目mock 模拟数据
│   ├── router                 // 路由
│   ├── store                  // 全局 store管理
│   ├── styles                 // 全局样式
│   ├── utils                  // 全局公用方法
│   ├── vendor                 // 公用vendor
│   ├── views                  // view
│   ├── App.vue                // 入口页面
│   ├── main.js                // 入口 加载组件 初始化等
│   └── permission.js          // 权限管理
├── static                     // 第三方不打包资源
│   └── Tinymce                // 富文本
├── .babelrc                   // babel-loader 配置
├── eslintrc.js                // eslint 配置项
├── .gitignore                 // git 忽略项
├── favicon.ico                // favicon图标
├── index.html                 // html模板
└── package.json               // package.json
```
