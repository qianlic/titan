<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <PageModal title="设置密码" v-model="isShowPasswordModle" @on-submit="changePassword">
      <Form label-position="left" :label-width="60">
        <FormItem label="新密码：">
          <Input v-model="newPassword" placeholder="请输入密码"/>
        </FormItem>
      </Form>
    </PageModal>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import PageModal from '../../components/modal/PageModal'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'user',
  data () {
    return {
      isShowPasswordModle: false,
      newPassword: null,
      columns: [{
        title: '用户编码',
        key: 'usercode'
      }, {
        title: '用户名',
        key: 'username'
      }, {
        title: '性别',
        width: 80,
        align: 'center',
        render: (h, {row}) => h('div', row.sex === 'M' ? '男' : '女')
      }, {
        title: '联系电话',
        key: 'mobile'
      }, {
        title: '最近登录时间',
        type: 'datetime',
        key: 'lastlogintime'
      }, {
        title: '状态',
        width: 80,
        align: 'center',
        render: (h, {row}) => h('Icon', {
          props: {
            type: 'record',
            size: 20,
            color: row.status ? '#19be6b' : '#ed3f14'
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
        title: '用户编码',
        name: 'usercode',
        type: 'input'
      }, {
        title: '用户名 ',
        name: 'username',
        type: 'input'
      }, {
        title: '用户状态',
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
      }, {
        title: '导 出',
        type: 'primary',
        icon: 'android-download',
        on: () => this.downloadDatas({
          filename: '用户列表.xls',
          params: this.$refs.table.searchInfo
        })
      }, {
        title: '设置密码',
        type: 'warning',
        icon: 'key',
        on: this.showPasswordModal
      }]
    }
  },
  components: {
    PageTable,
    PageModal
  },
  computed: mapGetters('user', [
    'datas',
    'selectIds'
  ]),
  methods: {
    ...mapActions('user', [
      'loadDatas',
      'downloadDatas',
      'removeDatas',
      'editStatus',
      'editPassword',
      'setSelectRows'
    ]),
    ...mapActions('role', [
      'loadAvailablelist'
    ]),
    createData () {
      this.$router.push({
        name: 'userFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'userFrom',
        params: row
      })
    },
    deleteData (ids) {
      if (ids.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.removeDatas(ids).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    changeStatus (status) {
      if (this.selectIds.length === 0) {
        this.$Message.info('请选择操作数据！')
        return
      }
      this.editStatus({
        'ids': this.selectIds,
        status
      }).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    showPasswordModal () {
      if (this.selectIds.length === 0) {
        this.$Message.success('请选择操作数据！')
        return
      }
      this.isShowPasswordModle = true
    },
    changePassword () {
      this.editPassword({
        'ids': this.selectIds,
        'password': this.newPassword
      }).then(response => {
        this.isShowPasswordModle = false
        this.$Message.success(response.message)
      })
    }
  },
  created () {
    this.loadAvailablelist()
  }
}
</script>
