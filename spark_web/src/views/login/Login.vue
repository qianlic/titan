<template>
  <div :style="{background: 'rebeccapurple',height: height + 'px'}">
    <LoginFrom class="login_from"/>
  </div>
</template>

<script>
import LoginFrom from '../../components/LoginFrom'

export default {
  data: () => {
    return {
      height: document.documentElement.clientHeight
    }
  },
  components: {
    LoginFrom
  },
  mounted: function () {
    const that = this
    window.onresize = () => {
      that.height = document.documentElement.clientHeight
    }
  }
}
</script>

<style scoped>
  .login_from {
    position: absolute;
    left: 50%;
    top: 20%;
    margin-left: -158px;
  }
</style>
