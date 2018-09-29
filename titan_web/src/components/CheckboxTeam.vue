<template>
  <CheckboxGroup class="checkbox-content" :value="value" @on-change="changeHandler">
    <div class="checkbox-div" v-for="item1 in chirdlist(0)" :key="item1.id">
      <div style="border-bottom:1px solid #f4f4f4;padding-bottom:3px;margin-bottom:3px">
        <Checkbox :label="item1.id">{{ item1.resourcename }}</Checkbox>
      </div>
      <Dropdown trigger="click" placement="bottom-start" v-for="item2 in chirdlist(item1.id)" :key="item2.id">
        <div style="width: 120px">
          <Checkbox :label="item2.id">
            {{ item2.resourcename }}
          </Checkbox>
          <Icon type="android-arrow-dropdown-circle" style="cursor:pointer"/>
        </div>
        <div slot="list" class="dropdown-menu">
          <div class="dropdown-head">
            功能列表
          </div>
          <div v-for="item3 in chirdlist(item2.id)" :key="item3.id">
            <Checkbox :label="item3.id">{{ item3.resourcename }}</Checkbox>
          </div>
        </div>
      </Dropdown>
    </div>
  </CheckboxGroup>
</template>

<script>
export default {
  name: 'checkbox-team',
  props: {
    'value': {
      type: Array
    },
    'datas': {
      type: Array,
      default: []
    }
  },
  methods: {
    changeHandler (s) {
      this.$emit('input', s)
    },
    chirdlist (id) {
      return this.datas.filter(x => x.parentid === id)
    }
  }
}
</script>

<style scoped>
  .checkbox-content {
    border-top:1px solid #dddee1
  }
  .checkbox-div {
    border:1px solid #dddee1;
    border-top:0px;
    padding:5px 15px
  }
  .dropdown-menu {
    width:150px;
    padding-left:15px
  }
  .dropdown-head {
    border-bottom:1px solid #f4f4f4;
    margin-bottom:6px
  }
</style>
