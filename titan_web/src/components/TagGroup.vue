<template>
  <div>
    <Tag v-for="(tag,idx) in tags" :color="tag.color" :key="idx" @on-close="handleDel(idx)" closable>
      {{tag.title}}
    </Tag>
    <Dropdown  trigger="custom" :visible="newTag.show" placement="bottom-start">
      <Button icon="ios-plus-empty" type="dashed" size="small" @click="newTag.show=true">添加标签</Button>
      <DropdownMenu slot="list" style="padding: 5px 10px">
        <Input v-model="newTag.title">
          <ColorPicker class="picker_div" slot="prepend" v-model="newTag.color"/>
        </Input>
        <div style="margin: 0 -2px">
          <Button type="ghost" size="small" @click="handleCal">取消</Button>
          <Button type="primary" size="small" @click="handleNew">新增</Button>
        </div>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
export default {
  name: 'tag-group',
  data () {
    return {
      newTag: {
        show: false,
        color: '#19be6b',
        title: ''
      }
    }
  },
  props: {
    'value': {
      type: String
    }
  },
  computed: {
    tags () {
      if (this.value) {
        return this.value.split(',').map(s => {
          const ss = s.split(':')
          return {
            'title': ss[0],
            'color': ss[1]
          }
        })
      }
    }
  },
  methods: {
    handleDel (idx) {
      const tags = this.tags
      tags.splice(idx, 1)
      this.$emit('input', tags.map(t => t.title + ':' + t.color).join(','))
    },
    handleNew () {
      let tagStr = ''
      if (this.value) {
        tagStr = this.value + ','
      }
      this.$emit('input', tagStr + this.newTag.title + ':' + this.newTag.color)
      this.newTag.show = false
    },
    handleCal () {
      this.newTag.show = false
    }
  }
}
</script>

<style scoped>
  .picker_div {
    margin: -5px -10px
  }
  .ivu-icon {
    display: none;
  }
</style>
