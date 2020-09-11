<template>
  <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'schedule',
  data () {
    return {
      columns: [{
        title: '任务名',
        key: 'name'
      }, {
        title: '所属组',
        key: 'group'
      }, {
        title: 'cron表达式',
        key: 'cronExpression'
      }, {
        title: '上次运行时间',
        type: 'datetime',
        key: 'previousTime'
      }, {
        title: '下次运行时间',
        type: 'datetime',
        key: 'nextTime'
      }, {
        title: '状态',
        key: 'state'
      }, {
        title: '操作',
        type: 'operation',
        buttons: [{
          title: '编辑',
          type: 'primary',
          click: this.editData
        }, {
          title: '删除',
          type: 'error',
          click: ({name, group}) => this.deleteData({name, group})
        }]
      }],
      searchs: [{
        title: '任务编码',
        name: 'usercode',
        type: 'input'
      }],
      buttons: [{
        title: '新 增',
        type: 'success',
        icon: 'plus',
        on: this.createData
      }, {
        title: '运 行',
        type: 'error',
        icon: 'ios-play',
        on: () => this.startJobs(this.selectRows)
      }, {
        title: '暂 停',
        type: 'warning',
        icon: 'ios-pause',
        on: () => this.pauseJobs(this.selectRows)
      }, {
        title: '恢 复',
        type: 'info',
        icon: 'refresh',
        on: () => this.resumeJobs(this.selectRows)
      }]
    }
  },
  components: {
    PageTable
  },
  computed: mapGetters('schedule', [
    'datas',
    'selectRows'
  ]),
  methods: {
    ...mapActions('schedule', [
      'loadDatas',
      'removeDatas',
      'startJob',
      'pauseJob',
      'resumeJob',
      'setSelectRows'
    ]),
    createData () {
      this.$router.push({
        name: 'scheduleFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'scheduleFrom',
        params: row
      })
    },
    deleteData (key) {
      this.removeDatas(key).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    startJobs (keys) {
      if (keys.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.startJob(keys[0]).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    pauseJobs (keys) {
      if (keys.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.pauseJob(keys[0]).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    resumeJobs (keys) {
      if (keys.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.resumeJob(keys[0]).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    }
  }
}
</script>
