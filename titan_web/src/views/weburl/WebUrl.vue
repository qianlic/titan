<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'page',
  data () {
    return {
      columns: [{
        title: '子域',
        key: 'subDomain'
      }, {
        title: '域名',
        key: 'domain'
      }, {
        title: 'Anchor',
        key: 'anchor'
      }, {
        title: '路径',
        key: 'path'
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
      }]
    }
  },
  components: {
    PageTable
  },
  computed: mapGetters('weburl', [
    'datas',
    'selectRows'
  ]),
  methods: {
    ...mapActions('weburl', [
      'loadDatas',
      'removeDatas',
      'editStatus',
      'setSelectRows'
    ]),
    deleteData (key) {
      this.removeDatas(key).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    }
  }
}
</script>
