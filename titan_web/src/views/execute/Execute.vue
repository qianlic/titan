<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <PageModal title="结果预览" v-model="show" @on-submit="show = false" :width="width">
      <div v-html="content"/>
    </PageModal>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import PageModal from '../../components/modal/PageModal'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'execute',
  data () {
    return {
      show: false,
      width: 1000,
      content: null,
      columns: [{
        title: '任务名',
        width: 80,
        key: 'taskname'
      }, {
        title: '所属组',
        width: 80,
        key: 'taskgroup'
      }, {
        title: '服务',
        key: 'server'
      }, {
        title: '路径',
        width: 180,
        key: 'path'
      }, {
        title: '参数',
        key: 'param'
      }, {
        title: '成功',
        key: 'success'
      }, {
        title: '信息',
        width: 100,
        key: 'message'
      }, {
        title: '执行时间',
        type: 'datetime',
        key: 'executetime'
      }, {
        title: '操作',
        type: 'operation',
        buttons: [{
          title: '查看',
          type: 'warning',
          click: ({result}) => this.showModle(result)
        }, {
          title: '删除',
          type: 'error',
          click: ({id}) => this.deleteData([id])
        }]
      }],
      searchs: [{
        title: '任务编码',
        name: 'usercode',
        type: 'input'
      }]
    }
  },
  components: {
    PageTable,
    PageModal
  },
  computed: mapGetters('execute', [
    'datas',
    'selectRows'
  ]),
  methods: {
    ...mapActions('execute', [
      'loadDatas',
      'removeDatas',
      'setSelectRows'
    ]),
    deleteData (ids) {
      this.removeDatas({ids}).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    },
    showModle (content) {
      this.content = content
      this.show = true
    }
  }
}
</script>
