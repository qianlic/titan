<template>
  <div class="foot">
    <i-tab-bar :current="current" @change="handleChange" fixed>
      <i-tab-bar-item key="home" icon="homepage" current-icon="homepage_fill" title="主页"/>
      <i-tab-bar-item key="goods" icon="barrage" current-icon="barrage_fill" title="商品"/>
      <i-tab-bar-item key="other" icon="interactive" title="留言"/>
    </i-tab-bar>
    <button class="kf_button" open-type="contact" session-from="weapp"/>
  </div>
</template>

<script>
export default {
  props: ['current'],
  methods: {
    handleChange ({target}) {
      if (target.key !== 'other') {
        this.$router.push(target.key)
      }
    }
  }
}
</script>

<style scoped>
  .foot {
    bottom: 0;
    position: fixed;
    width: 100%;
  }
  .kf_button {
    z-index:5;
    right: 0;
    width: 30%;
    height: 50px;
    margin-right: 0;
    background-color: rgba(255, 255, 255, 0);
  }
  .kf_button::after{
    border: none;
  }
</style>
