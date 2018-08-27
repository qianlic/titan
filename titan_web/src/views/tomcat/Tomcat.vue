<template>
  <Table border size="small" :columns="columns" :data="datas"/>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'tomcat',
  data () {
    return {
      columns: [{
        title: '连接器',
        width: 120,
        key: 'name'
      }, {
        title: '最大线程数',
        key: 'maxThreads'
      }, {
        title: '当前线程数',
        key: 'currentThreadCount'
      }, {
        title: '正忙线程数',
        key: 'currentThreadsBusy'
      }, {
        title: '接收字节',
        key: 'bytesReceived'
      }, {
        title: '发送字节',
        key: 'bytesSent'
      }, {
        title: '请求数',
        width: 75,
        key: 'requestCount'
      }, {
        title: '异常数',
        width: 75,
        key: 'errorCount'
      }, {
        title: '处理时间',
        key: 'processingTime'
      }, {
        title: '最大处理时间',
        key: 'maxTime'
      }]
    }
  },
  computed: {
    ...mapGetters('tomcat', [
      'datas'
    ])
  },
  methods: {
    ...mapActions('tomcat', [
      'loadDatas'
    ])
  },
  created () {
    this.loadDatas()
  }
}
</script>
