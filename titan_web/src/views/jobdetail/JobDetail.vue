<template>
  <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'jobdetail',
  data () {
    return {
      columns: [{
        title: '任务名',
        key: 'name'
      }, {
        title: '所属组',
        key: 'group'
      }, {
        title: '服务',
        key: 'service'
      }, {
        title: '方法',
        key: 'method'
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
        on: () => this.runJobs(this.selectRows)
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
  computed: mapGetters('jobdetail', [
    'datas',
    'selectRows'
  ]),
  methods: {
    ...mapActions('jobdetail', [
      'loadDatas',
      'removeDatas',
      'runJob',
      'pauseJob',
      'resumeJob',
      'setSelectRows'
    ]),
    createData () {
      this.$router.push({
        name: 'jobdetailFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'jobdetailFrom',
        params: row
      })
    },
    deleteData (key) {
      this.removeDatas(key).then(response => {
        if (response.status === 0) {
          this.$refs.table.pageChange()
          this.$Message.success(response.message)
        }
      })
    },
    runJobs (keys) {
      if (keys.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.runJob(keys[0]).then(response => {
          if (response.status === 0) {
            this.$refs.table.pageChange()
            this.$Message.success(response.message)
          }
        })
      }
    },
    pauseJobs (keys) {
      if (keys.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.pauseJob(keys[0]).then(response => {
          if (response.status === 0) {
            this.$refs.table.pageChange()
            this.$Message.success(response.message)
          }
        })
      }
    },
    resumeJobs (keys) {
      if (keys.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.resumeJob(keys[0]).then(response => {
          if (response.status === 0) {
            this.$refs.table.pageChange()
            this.$Message.success(response.message)
          }
        })
      }
    }
  }
}
</script>
