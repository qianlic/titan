<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="角色编码">
        <Input v-model="formInline.rolecode"/>
      </FormItem>
      <FormItem label="角色名称">
        <Input v-model="formInline.rolename"/>
      </FormItem>
      <FormItem label="描述">
        <Input v-model="formInline.description" type="textarea" :rows="4"/>
      </FormItem>
      <FormItem label="资源列表">
        <Card v-for="item1 in availablelist.filter(x => x.type === '1')" :key="item1.id">
          <div style="border-bottom: 1px solid #f4f4f4;padding-bottom:6px;margin-bottom:6px">
            <CheckboxGroup v-model="resourceids">
              <Checkbox :label="item1.id">{{ item1.resourcename }}</Checkbox>
            </CheckboxGroup>
          </div>
          <CheckboxGroup v-model="resourceids">
            <span v-for="item2 in availablelist" v-if="item2.parentid === item1.id" :key="item2.id">
              <Checkbox :label="item2.id">
                {{ item2.resourcename }}
              </Checkbox>[
              <Checkbox v-for="item3 in availablelist" v-if="item3.parentid === item2.id" :key="item3.id" :label="item3.id">
                {{ item3.resourcename }}
              </Checkbox>]
              <br/>
            </span>
          </CheckboxGroup>
        </Card>
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

export default {
  name: 'role-from',
  data () {
    const {id, rolecode, rolename, description, status} = this.$route.params
    return {
      indeterminate: false,
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
    }
  },
  created () {
    this.resourceids = this.$route.params.resourceids
      .split(',').map(resourceid => Number.parseInt(resourceid))
  }
}
</script>
