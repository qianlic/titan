<template>
  <Modal @on-visible-change="visibleChange" :value="value" :width="width">
    <p slot="header" class="head_div">{{title}}</p>
    <div style="margin: 20px 10px">
      <slot/>
    </div>
    <div slot="footer">
      <Button type="ghost" size="small" @click="onCancel">取 消</Button>
      <Button type="primary" size="small" @click="onSubmit">确 认</Button>
    </div>
  </Modal>
</template>

<script>
export default {
  name: 'page-modal',
  props: {
    'title': {
      type: String,
      required: true
    },
    'value': {
      type: Boolean,
      default: false
    },
    'width': {
      type: Number,
      default: 520
    }
  },
  methods: {
    onSubmit () {
      this.$emit('on-submit')
    },
    onCancel () {
      this.visibleChange(false)
    },
    visibleChange (show) {
      this.$emit('input', show)
    }
  }
}
</script>

<style scoped>
  .head_div{
    font-weight:normal;
    font-size:14px;
    line-height:24px
  }
</style>
