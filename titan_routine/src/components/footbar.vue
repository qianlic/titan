<template>
  <div>
    <i-tab-bar :current="current" @change="handleChange" fixed>
      <i-tab-bar-item key="home" icon="homepage" current-icon="homepage_fill" title="主页"/>
      <i-tab-bar-item key="goods" icon="barrage" current-icon="barrage_fill" title="商品"/>
    </i-tab-bar>
  </div>
</template>

<script>
export default {
  props: ['current'],
  methods: {
    handleChange ({target}) {
      this.$router.push(target.key)
    }
  }
}
</script>
