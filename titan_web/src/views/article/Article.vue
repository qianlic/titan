<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <PageModal title="内容预览" v-model="show" @on-submit="show = false" :width="width">
      <div v-html="content"/>
    </PageModal>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import PageModal from '../../components/modal/PageModal'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'article',
  data () {
    return {
      show: false,
      width: 1000,
      content: null,
      columns: [{
        title: '标题',
        key: 'title'
      }, {
        title: '作者',
        key: 'author'
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
        title: '创建时间',
        type: 'datetime',
        key: 'ts'
      }, {
        title: '操作',
        type: 'operation',
        width: 200,
        buttons: [{
          title: '预览',
          type: 'warning',
          click: ({content}) => this.showModle(content)
        }, {
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
        title: '标题',
        name: 'title',
        type: 'input'
      }, {
        title: '作者 ',
        name: 'author',
        type: 'input'
      }, {
        title: '状态',
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
      }]
    }
  },
  components: {
    PageTable,
    PageModal
  },
  computed: mapGetters('article', [
    'datas',
    'selectIds'
  ]),
  methods: {
    ...mapActions('article', [
      'loadDatas',
      'removeDatas',
      'setSelectRows'
    ]),
    createData () {
      this.$router.push({
        name: 'articleFrom',
        params: {iscreate: true}
      })
    },
    editData (row) {
      this.$router.push({
        name: 'articleFrom',
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
    showModle (content) {
      this.content = content
      this.show = true
    }
  }
}
</script>
