<template>
  <div class="div-nav">
    <transition-group name="taglist-moving-animation">
      <Tag v-for="(page,idx) in cachepages" :key="idx" :color="page.color" :name="page.code"
           @click.native="loadPage(page.code)" @on-close="closePage" type="dot" :closable="idx>0">
        {{ page.name }}
      </Tag>
    </transition-group>
  </div>
</template>

<script>
export default {
  name: 'navigation-bar',
  props: {
    'cachepages': {
      type: Array,
      required: true
    }
  },
  methods: {
    loadPage: function (code) {
      this.$emit('load_page', code)
    },
    closePage: function (event, code) {
      this.$emit('close_page', code)
    }
  }
}
</script>

<style scoped>
  .div-nav {
    padding: 5px 0px
  }
</style>
