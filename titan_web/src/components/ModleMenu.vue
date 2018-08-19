<template>
  <!-- 目录菜单. -->
  <Menu theme="light" width="auto" @on-select="loadPage">
    <Submenu :name="menu.resourcecode" :key="menu.id" v-for="menu in resources" v-if="menu.parentid == 0">
      <template slot="title">
        <Icon :type="menu.ico"></Icon>
        {{menu.resourcename}}
      </template>
      <MenuItem :name="item.resourcecode" :key="item.id" v-for="item in resources" v-if="item.parentid == menu.id">
        <Icon :type="item.ico"></Icon>
        {{item.resourcename}}
      </MenuItem>
    </Submenu>
  </Menu>
</template>

<script>
export default {
  name: 'sider-menu',
  props: {
    'resources': {
      type: Array,
      required: true
    }
  },
  methods: {
    loadPage: function (code) {
      this.$emit('load_page', code)
    }
  }
}
</script>
