<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <PageModal title="堆栈信息" v-model="isShowStackTraceModle" @on-submit="hideStackTraceModle">
      <p class="soft-wrap" v-for="(stack,idx) in stackTrace" :key="idx">
        {{stack}}
      </p>
    </PageModal>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import PageModal from '../../components/modal/PageModal'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'thread',
  data () {
    return {
      isShowStackTraceModle: false,
      stackTrace: [],
      columns: [{
        title: '线程编号',
        width: 90,
        key: 'globalThreadId'
      }, {
        title: '线程名',
        key: 'name'
      }, {
        title: '优先级',
        width: 80,
        align: 'center',
        key: 'priority'
      }, {
        title: 'cpu时间',
        width: 90,
        align: 'center',
        key: 'cpuTimeMillis'
      }, {
        title: 'user时间',
        width: 90,
        align: 'center',
        key: 'userTimeMillis'
      }, {
        title: '守护线程',
        width: 90,
        align: 'center',
        render: (h, {row}) => h('div', row.daemon ? '是' : '否')
      }, {
        title: '死锁',
        width: 70,
        align: 'center',
        render: (h, {row}) => h('div', row.deadlocked ? '是' : '否')
      }, {
        title: '状态',
        width: 140,
        key: 'state'
      }, {
        title: '操作',
        type: 'operation',
        buttons: [{
          title: '详情',
          type: 'primary',
          click: (row) => this.showStackTraceModle(row)
        }, {
          title: '中断',
          type: 'error',
          click: ({id}) => this.interrupt([id])
        }]
      }],
      searchs: [{
        title: '守护线程',
        name: 'daemon',
        type: 'select',
        options: [{
          title: '是',
          value: 1
        }, {
          title: '否',
          value: 0
        }]
      }, {
        title: '死锁',
        name: 'deadlocked',
        type: 'select',
        options: [{
          title: '是',
          value: 1
        }, {
          title: '否',
          value: 0
        }]
      }, {
        title: '状态',
        name: 'state',
        type: 'select',
        options: [{
          title: 'NEW',
          value: 'NEW'
        }, {
          title: 'RUNNABLE',
          value: 'RUNNABLE'
        }, {
          title: 'BLOCKED',
          value: 'BLOCKED'
        }, {
          title: 'WAITING',
          value: 'WAITING'
        }, {
          title: 'TIMED_WAITING',
          value: 'TIMED_WAITING'
        }, {
          title: 'TERMINATED',
          value: 'TERMINATED'
        }]
      }],
      buttons: [{
        title: '中 断',
        type: 'error',
        icon: 'close',
        on: () => this.interrupt(this.selectIds)
      }]
    }
  },
  components: {
    PageTable,
    PageModal
  },
  computed: mapGetters('thread', [
    'datas',
    'selectIds'
  ]),
  methods: {
    ...mapActions('thread', [
      'loadDatas',
      'interruptDatas',
      'setSelectRows'
    ]),
    interrupt (ids) {
      if (ids.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.interruptDatas({ids}).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    showStackTraceModle (row) {
      this.stackTrace = row.stackTrace
      this.isShowStackTraceModle = true
    },
    hideStackTraceModle () {
      this.isShowStackTraceModle = false
    }
  }
}
</script>

<style>
  .soft-wrap {
    word-break: normal;
    word-wrap: break-word;
  }
</style>
