<template>
  <Header style="padding: 0 20px">
    <div mode="horizontal" theme="dark">
      <div class="layout-logo">
        <Icon type="cube"></Icon>
        后台管理系统
      </div>
      <div class="layout-nav">
        <Dropdown trigger="click" placement="bottom-end" @on-click="clickItem">
          <div name="logout">
            <Avatar icon="person" :src="token.imgurl"/>
            {{token.username}}
          </div>
          <DropdownMenu slot="list">
            <DropdownItem name="github">
              <Icon class="dropdown-icon" type="social-github"/>
              GitHub
            </DropdownItem>
            <DropdownItem name="passwd">
              <Icon class="dropdown-icon" type="key"/>
              修改密码
            </DropdownItem>
            <DropdownItem name="logout">
              <Icon class="dropdown-icon" type="log-out"/>
              退出登录
            </DropdownItem>
          </DropdownMenu>
        </Dropdown >
      </div>
    </div>
  </Header>
</template>

<script>
export default {
  name: 'header-menu',
  props: {
    'token': {
      type: Object,
      default: {}
    }
  },
  methods: {
    logout: function () {
      this.$emit('log_out')
    },
    clickItem: function (name) {
      if (name === 'logout') {
        this.logout()
      } else if (name === 'github') {
        window.open('https://github.com/qianlic/titan')
      }
    }
  }
}
</script>

<style scoped>
  .layout-logo {
    width: 140px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    color: white;
    line-height: 30px;
    text-align: center;
    font-size: 18px;
  }

  .layout-nav {
    color: white;
    float: right;
    cursor:pointer;
    font-size: 14px;
  }

  .dropdown-icon {
    width: 15px;
    text-align: center
  }
</style>
