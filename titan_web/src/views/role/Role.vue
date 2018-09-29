<template>
  <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'role',
  data () {
    return {
      columns: [{
        title: '角色编码',
        key: 'rolecode'
      }, {
        title: '角色名',
        key: 'rolename'
      }, {
        title: '状态',
        width: 120,
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
      searchs: [{
        title: '角色编码',
        name: 'rolecode',
        type: 'input'
      }, {
        title: '角色名 ',
        name: 'rolename',
        type: 'input'
      }, {
        title: '角色状态',
        name: 'status',
        type: 'select',
        options: [{
          title: '启 用',
          value: 1
        }, {
          title: '禁 用',
          value: 0
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
        type: 'info',
        icon: 'unlocked',
        on: () => this.changeStatus(true)
      }, {
        title: '禁 用',
        type: 'info',
        icon: 'locked',
        on: () => this.changeStatus(false)
      }]
    }
  },
  components: {
    PageTable
  },
  computed: mapGetters('role', [
    'datas',
    'selectIds'
  ]),
  methods: {
    ...mapActions('role', [
      'loadDatas',
      'removeDatas',
      'editStatus',
      'setSelectRows'
    ]),
    ...mapActions('resource', [
      'loadAvailablelist'
    ]),
    createData () {
      this.$router.push({
        name: 'roleFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'roleFrom',
        params: row
      })
    },
    deleteData (ids) {
      if (ids.length === 0) {
        this.$Message.success('请选择操作数据！')
      } else {
        this.removeDatas({ids}).then(response => {
          if (response.status === 0) {
            this.$refs.table.pageChange()
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
            this.$refs.table.pageChange()
            this.$Message.success(response.message)
          }
        })
      }
    }
  },
  created () {
    this.loadAvailablelist()
  }
}
</script>
