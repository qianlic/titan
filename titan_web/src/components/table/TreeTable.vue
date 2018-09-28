<template>
  <div>
    <ToolBar v-if="buttons" :buttons="buttons" @refresh-page="loadData" refreshable/>
    <Table border size="small" @on-selection-change="selectionChange"
           :loading="loading" :columns="heads" :data="showData"/>
  </div>
</template>

<script>
import ToolBar from './ToolBar'

export default {
  name: 'tree-table',
  data () {
    return {
      loading: false
    }
  },
  components: {
    ToolBar
  },
  props: {
    'columns': {
      type: Array,
      required: true
    },
    'buttons': {
      type: Array
    },
    'data': {
      type: Array,
      default: []
    },
    'selection': {
      type: Boolean,
      default: false
    }
  },
  computed: {
    heads () {
      let result = this.columns.map(x => {
        if (x.type === 'expand') {
          return {
            title: x.title,
            render: (h, {row}) => {
              if (row.level === 1) {
                return h('div', [
                  h('span', {
                    'class': 'table-row-expand',
                    on: {click: () => x.click(row)}
                  },
                  row['expand'] ? '-' : '+'),
                  row[x.key]
                ])
              } else if (row.level === 2) {
                return h('div', {
                  'style': 'margin-left:20px'
                }, [
                  h('span', {
                    'class': ['table-row-expand'],
                    on: {click: () => x.click(row)}
                  },
                  row['expand'] ? '-' : '+'),
                  row[x.key]
                ])
              } else {
                return h('div', {
                  'style': 'margin-left:40px'
                },
                row[x.key])
              }
            }
          }
        } else if (x.type === 'operation') {
          return {
            title: x.title,
            width: 150,
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
    },
    showData () {
      return this.data.filter(x => x['display'] === true)
    }
  },
  methods: {
    selectionChange (selects) {
      this.$emit('select-row', selects)
    },
    loadData () {
      this.loading = true
      this.$emit('load-data')
      this.selectionChange([])
      this.loading = false
    }
  },
  activated () {
    this.loadData()
  }
}
</script>

<style>
  .table-row-expand {
    margin-right: 8px;
    cursor: pointer;
    display: inline-block;
    width: 14px;
    height: 14px;
    text-align: center;
    line-height: 14px;
    border: 1px solid #e8e8e8;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background: #fff;
  }
</style>
