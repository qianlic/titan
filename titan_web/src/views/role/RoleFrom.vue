<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="角色编码">
        <Input v-model="formInline.rolecode"/>
      </FormItem>
      <FormItem label="角色名称">
        <Input v-model="formInline.rolename"/>
      </FormItem>
      <FormItem label="资源列表">
        <CheckboxGroup v-model="resourceids" style="border-top:1px solid #dddee1;">
          <div v-for="item1 in chirdlist(0)" :key="item1.id" style="border:1px solid #dddee1;border-top:0px;padding:5px 15px">
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
              <DropdownMenu slot="list" style="width:150px;padding-left:15px">
                <div style="border-bottom:1px solid #f4f4f4;margin-bottom:6px">
                  功能列表
                </div>
                <div v-for="item3 in chirdlist(item2.id)" :key="item3.id">
                  <Checkbox :label="item3.id">{{ item3.resourcename }}</Checkbox>
                </div>
              </DropdownMenu>
            </Dropdown>
          </div>
        </CheckboxGroup>
      </FormItem>
      <FormItem label="描述">
        <QuillEditor v-model="formInline.description"/>
      </FormItem>
      <FormItem label="状态">
        <i-switch v-model="formInline.status" size="large">
          <span slot="open">启用</span>
          <span slot="close">禁用</span>
        </i-switch>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit">提交</Button>
        <Button type="ghost" style="margin-left: 8px" @click="handleCancel">取消</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import QuillEditor from '../../components/VueQuillEditor'

export default {
  name: 'role-from',
  data () {
    const {id, rolecode, rolename, description, status} = this.$route.params
    return {
      visible: false,
      checkAll: false,
      resourceids: [],
      id,
      formInline: {
        rolecode,
        rolename,
        description,
        status
      }
    }
  },
  components: {
    QuillEditor
  },
  computed: {
    ...mapGetters('resource', [
      'availablelist',
      'availableids',
      'availablelength'
    ])
  },
  methods: {
    ...mapActions('role', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      const params = {
        'resourceids': this.resourceids.join(','),
        ...this.formInline
      }
      if (this.$route.params.iscreate) {
        this.createData(params).then(response => {
          if (response.status === 0) {
            this.$Message.success(response.message)
            this.handleCancel()
          } else {
            this.$Message.error(response.message)
          }
        })
      } else {
        this.editDatas({
          'id': this.id,
          params
        }).then(response => {
          if (response.status === 0) {
            this.$Message.success(response.message)
            this.handleCancel()
          } else {
            this.$Message.error(response.message)
          }
        })
      }
    },
    handleCancel () {
      this.$router.go(-1)
    },
    chirdlist (id) {
      return this.availablelist.filter(x => x.parentid === id)
    }
  },
  created () {
    if (!this.$route.params.iscreate) {
      this.resourceids = this.$route.params.resourceids
        .split(',').map(resourceid => Number.parseInt(resourceid))
    }
  }
}
</script>
