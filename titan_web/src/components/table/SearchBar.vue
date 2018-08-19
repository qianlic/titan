<template>
  <div style="margin: 10px 0px">
    <span  v-for="(search,idx) in searchs" :key="idx">
      <Select size="small" v-if="search.type === 'select'" v-model="searchInfo[search.name]"
              :placeholder="search.title" :style="idx>0?[widthStyle,spaceStyle]:widthStyle" clearable>
        <Option v-for="option in search.options" :value="option.value" :key="option.value">
          {{option.title}}
        </Option>
      </Select>
      <Input size="small" v-if="search.type === 'input'" v-model="searchInfo[search.name]"
             :placeholder="search.title" :style="idx>0?[widthStyle,spaceStyle]:widthStyle" clearable/>
    </span>
    <span style="float: right;">
      <Button type="primary" size="small" @click="doSearch"> 搜 索 </Button>
      <Button type="ghost" size="small" @click="doClear" :style="spaceStyle"> 清 空 </Button>
    </span>
  </div>
</template>

<script>
export default {
  name: 'search-bar',
  data () {
    return {
      widthStyle: {
        'width': '120px'
      },
      spaceStyle: {
        'margin-left': '8px'
      }
    }
  },
  props: {
    'searchInfo': {
      type: Object,
      default: function () {
        return {}
      }
    },
    'searchs': {
      type: Array,
      required: true
    }
  },
  methods: {
    doSearch: function () {
      this.$emit('do-search')
    },
    doClear: function () {
      this.$emit('do-clear')
    }
  }
}
</script>
