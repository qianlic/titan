<template>
  <TreeTable ref="table" :columns="columns" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import TreeTable from '../../components/table/TreeTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'resource',
  data () {
    return {
      columns: [{
        title: '资源编码',
        key: 'resourcecode',
        type: 'expand',
        click: ({id}) => this.expandData(id)
      }, {
        title: '资源名',
        key: 'resourcename'
      }, {
        title: '资源描述',
        key: 'description'
      }, {
        title: '类型',
        width: 70,
        align: 'center',
        render: (h, {row}) => h('div', ['分组', '资源', '权限'][row.type - 1])
      }, {
        title: '状态',
        width: 80,
        align: 'center',
        render: (h, {row}) => h('Icon', {
          props: {
            type: 'record',
            size: 20,
            color: row.status ? '#2d8cf0' : '#ed3f14'
          }
        })
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
          click: ({id}) => this.deleteData([id])
        }]
      }],
      buttons: [{
        title: '新 增',
        type: 'success',
        icon: 'plus',
        on: this.createData
      }, {
        title: '删 除',
        type: 'error',
        icon: 'close',
        on: () => this.deleteData(this.selectIds)
      }, {
        title: '启 用',
        type: 'warning',
        icon: 'unlocked',
        on: () => this.changeStatus(true)
      }, {
        title: '禁用',
        type: 'warning',
        icon: 'locked',
        on: () => this.changeStatus(false)
      }]
    }
  },
  components: {
    TreeTable
  },
  computed: mapGetters('resource', [
    'datas'
  ]),
  methods: {
    ...mapActions('resource', [
      'loadDatas',
      'expandData',
      'removeDatas',
      'editStatus',
      'setSelectRows'
    ]),
    createData () {
      this.$router.push({
        name: 'resourceFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'resourceFrom',
        params: row
      })
    },
    deleteData (ids) {
      if (ids.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.removeDatas({ids}).then(response => {
          if (response.status === 0) {
            this.$Message.success(response.message)
          }
        })
      }
    },
    changeStatus (status) {
      if (this.selectIds.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.editStatus({
          'ids': this.selectIds,
          status
        }).then(response => {
          if (response.status === 0) {
            this.$refs.table.loadData()
            this.$Message.success(response.message)
          }
        })
      }
    }
  }
}
</script>
