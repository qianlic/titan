<template>
  <div ref="echarts"/>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'echarts',
  data () {
    return {
      myChart: undefined
    }
  },
  props: {
    'title': {
      type: Object
    },
    'data': {
      type: Array,
      required: true
    }
  },
  mounted () {
    this.myChart = echarts.init(this.$refs.echarts)
  },
  watch: {
    data: function () {
      this.myChart.setOption({
        title: {
          text: this.title,
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: {
          name: this.title,
          type: 'pie',
          radius: '55%',
          data: this.data
        }
      })
    }
  }
}
</script>
