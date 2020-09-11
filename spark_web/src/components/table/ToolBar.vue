<template>
  <div style="margin: 10px 0px">
    <Button size="small" v-for="(button,idx) in buttons" :style="idx > 0 ? spaceStyle:{}"
            :key="idx" :type="button.type" :icon="button.icon" @click="button.on">
      {{button.title}}
    </Button>
    <Button size="small" v-if="refreshable" style="float:right" type="dashed" icon="refresh" @click="refreshPage">
      刷 新
    </Button>
  </div>
</template>

<script>
export default {
  name: 'tool-bar',
  data () {
    return {
      spaceStyle: {
        'margin-left': '8px'
      }
    }
  },
  props: {
    'buttons': {
      type: Array,
      required: true
    },
    'refreshable': {
      type: Boolean,
      default: false
    }
  },
  methods: {
    refreshPage: function () {
      this.$emit('refresh-page')
    }
  }
}
</script>
