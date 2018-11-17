<template>
  <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
             @select-row="setSelectRows" @load-data="loadDatas" selection/>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'crawler',
  data () {
    return {
      columns: [{
        title: '编码',
        width: 120,
        key: 'code'
      }, {
        title: '爬虫名称',
        width: 120,
        key: 'name'
      }, {
        title: '地址',
        key: 'url'
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
          click: ({id}) => this.deleteData(id)
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
        on: () => this.runCrawler({'ids': this.selectIds})
      }]
    }
  },
  components: {
    PageTable
  },
  computed: mapGetters('crawler', [
    'datas',
    'selectIds'
  ]),
  methods: {
    ...mapActions('crawler', [
      'loadDatas',
      'removeDatas',
      'editStatus',
      'runCrawler',
      'setSelectRows'
    ]),
    createData () {
      this.$router.push({
        name: 'crawlerFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'crawlerFrom',
        params: row
      })
    },
    deleteData (id) {
      this.removeDatas({ids: [id]}).then(response => {
        this.$refs.table.pageChange()
        this.$Message.success(response.message)
      })
    }
  }
}
</script>
