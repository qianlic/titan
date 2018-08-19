<template>
  <Header>
    <Menu mode="horizontal" theme="dark" @on-select="logout">
      <div class="layout-logo"></div>
      <div class="layout-nav">
        <MenuItem name="logout">
          <Avatar icon="person"/>
          {{username}}
        </MenuItem>
      </div>
    </Menu>
  </Header>
</template>

<script>
export default {
  name: 'header-menu',
  props: {
    'username': {
      type: String,
      default: 'helloworld'
    }
  },
  methods: {
    logout: function () {
      this.$emit('log_out')
    }
  }
}
</script>

<style scoped>
  .layout-logo {
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }

  .layout-nav {
    width: 150px;
    margin: 0 auto;
    margin-right: 0px;
  }
</style>
