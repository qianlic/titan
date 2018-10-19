<template>
  <div class="layout">
    <Layout>
      <HeaderMenu :token="token" @log_out="logout"/>
      <Layout>
        <Sider hide-trigger style="background:#fff;">
          <!-- 目录菜单. -->
          <ModleMenu :resources="token.resources" @load_page="loadPage"/>
        </Sider>
        <Layout style="padding:0 20px 20px">
          <!-- 导航条. -->
          <NavigationBar :cachepages="cachepages" @load_page="loadPage" @close_page="closePage"/>
          <Content :style="{padding: '24px',background: '#fff',minHeight: height}">
            <keep-alive :include="cachepages.map(page=>page.code)">
              <!-- 路由出口 -->
              <!-- 路由匹配到的组件将渲染在这里 -->
              <router-view/>
            </keep-alive>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  </div>
</template>

<script>
import HeaderMenu from '../components/HeaderMenu'
import ModleMenu from '../components/ModleMenu'
import NavigationBar from '../components/NavigationBar'
import localStore from '../store/localStore'
import request from '../api/user'
import {mapActions, mapGetters} from 'vuex'

export default {
  name: 'index',
  data: () => {
    const h = document.documentElement.clientHeight
    return {
      height: h - 132 + 'px'
    }
  },
  computed: mapGetters([
    'token',
    'cachepages',
    'activepage'
  ]),
  components: {
    HeaderMenu,
    ModleMenu,
    NavigationBar
  },
  methods: {
    ...mapActions([
      'loadTokenInfo',
      'openPageInfo',
      'closePageInfo'
    ]),
    loadPage: function (code) {
      this.openPageInfo(code)
      this.$router.push(code)
    },
    closePage: function (code) {
      this.closePageInfo(code)
      this.$router.push(this.activepage.code)
    },
    logout: function () {
      request.logout().then(response => {
        if (response.success) {
          localStore.removeStoreItem('AUTH_TOKEN')
          this.$router.push('/login')
        }
      })
    }
  },
  created: function () {
    request.token().then(response => {
      if (response.success) {
        this.loadTokenInfo(response.data)
      }
    })
  },
  mounted: function () {
    const that = this
    window.onresize = () => {
      const h = document.documentElement.clientHeight
      that.height = h - 132 + 'px'
    }
  }
}
</script>

<style scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
</style>
