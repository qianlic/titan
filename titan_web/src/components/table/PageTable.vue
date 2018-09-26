<template>
  <div>
    <SearchBar v-if="searchs" :search-info="searchInfo" :searchs="searchs"
               @do-search="pageChange" @do-clear="searchClear"/>
    <ToolBar v-if="buttons" :buttons="buttons" @refresh-page="pageChange" refreshable/>
    <Table border size="small" @on-selection-change="selectionChange"
           :loading="loading" :columns="heads" :data="data.list"/>
    <div class="page_div">
      <div class="float_right">
        <Page show-total="show-total" size="small" @on-change="pageChange"
              :total="data.total" :page-size="size" :current="page"/>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBar from './SearchBar'
import ToolBar from './ToolBar'
import formatDate from '../../utils/date'

export default {
  name: 'page-table',
  data () {
    return {
      page: this.current,
      searchInfo: {},
      loading: false
    }
  },
  components: {
    SearchBar,
    ToolBar
  },
  props: {
    'columns': {
      type: Array,
      required: true
    },
    'searchs': {
      type: Array
    },
    'buttons': {
      type: Array
    },
    'data': {
      type: Object,
      default: {
        list: [],
        total: 0
      }
    },
    'size': {
      type: Number,
      default: 10
    },
    'current': {
      type: Number,
      default: 1
    },
    'selection': {
      type: Boolean,
      default: false
    }
  },
  computed: {
    heads () {
      let result = this.columns.map(x => {
        if (x.type === 'datetime') {
          return {
            title: x.title,
            render: (h, {row}) => {
              const date = formatDate(new Date(row[x.key]), 'yyyy-MM-dd hh:mm:ss')
              return h('div', date)
            }
          }
        } else if (x.type === 'image') {
          return {
            title: x.title,
            width: x.width ? x.width : 100,
            render: (h, {row}) => {
              return h('img', {
                attrs: {
                  width: '100%',
                  src: row[x.key]
                }
              }, '')
            }
          }
        } else if (x.type === 'operation') {
          return {
            title: x.title,
            width: x.width ? x.width : 150,
            align: 'center',
            render: (h, {row}) => {
              const buttons = x.buttons.map((y, idx) => {
                const button = {
                  props: {
                    type: y.type,
                    size: 'small'
                  },
                  on: {click: () => y.click(row)}
                }
                if (idx > 0) {
                  button['style'] = {marginLeft: '5px'}
                }
                return h('Button', button, y.title)
              })
              return h('div', buttons)
            }
          }
        }
        return x
      })
      if (this.selection) {
        result = [{
          type: 'selection',
          width: 60,
          align: 'center'
        },
        ...result]
      }
      return result
    }
  },
  methods: {
    pageChange (page = this.page) {
      if (this.page !== page) {
        this.page = page
      }
      const params = this.searchInfo
      for (const key in params) {
        if (params[key] === '') {
          delete params[key]
        }
      }
      this.loadData({
        'start': this.size * (this.page - 1),
        'size': this.size,
        params
      })
    },
    selectionChange (selects) {
      this.$emit('select-row', selects)
    },
    searchClear () {
      this.searchInfo = {}
    },
    loadData (params) {
      this.loading = true
      this.$emit('load-data', params)
      this.selectionChange([])
      this.loading = false
    }
  },
  activated () {
    this.pageChange()
  }
}
</script>

<style scoped>
  .page_div {
    margin: 10px;
    overflow: hidden
  }

  .float_right {
    float: right;
  }
</style>
