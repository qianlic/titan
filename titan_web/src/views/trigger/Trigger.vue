<template>
  <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'trigger',
  data () {
    return {
      columns: [{
        title: '触发器名',
        key: 'name'
      }, {
        title: '所属组',
        key: 'group'
      }, {
        title: '优先级',
        key: 'priority'
      }, {
        title: 'cron表达式',
        key: 'cronExpression'
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
        title: '暂 停',
        type: 'warning',
        icon: 'ios-pause',
        on: () => this.pauseTiggers(this.selectRows)
      }, {
        title: '恢 复',
        type: 'info',
        icon: 'refresh',
        on: () => this.resumeTiggers(this.selectRows)
      }]
    }
  },
  components: {
    PageTable
  },
  computed: mapGetters('trigger', [
    'datas',
    'selectRows'
  ]),
  methods: {
    ...mapActions('trigger', [
      'loadDatas',
      'removeDatas',
      'pauseTigger',
      'resumeTigger',
      'setSelectRows'
    ]),
    ...mapActions('jobdetail', [
      'loadAvailablelist'
    ]),
    createData () {
      this.$router.push({
        name: 'triggerFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'triggerFrom',
        params: row
      })
    },
    deleteData (key) {
      this.removeDatas(key).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    pauseTiggers (keys) {
      if (keys.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.pauseTigger(keys[0]).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    resumeTiggers (keys) {
      if (keys.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.resumeTigger(keys[0]).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    }
  },
  created () {
    this.loadAvailablelist()
  }
}
</script>
