<template>
  <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
    <FormItem label="角色编码">
      <Input v-model="formInline.rolecode"/>
    </FormItem>
    <FormItem label="角色名称">
      <Input v-model="formInline.rolename"/>
    </FormItem>
    <FormItem label="资源列表">
      <CheckboxTeam v-model="resourceids" :datas="availablelist"/>
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
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import QuillEditor from '../../components/VueQuillEditor'
import CheckboxTeam from '../../components/CheckboxTeam'

export default {
  name: 'role-from',
  data () {
    return {
      visible: false,
      checkAll: false,
      id: null,
      resourceids: [],
      formInline: {}
    }
  },
  components: {
    QuillEditor,
    CheckboxTeam
  },
  computed: {
    ...mapGetters('resource', [
      'availablelist',
      'availableids',
      'availablelength'
    ]),
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('role', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      let method = this.createData
      let params = {
        'resourceids': this.resourceids.join(','),
        ...this.formInline
      }
      if (!this.requsetParams.iscreate) {
        method = this.editDatas
        params = {
          'id': this.id,
          params
        }
      }
      method(params).then(response => {
        this.$Message.success(response.message)
        this.handleCancel()
      })
    },
    handleCancel () {
      this.$router.go(-1)
    }
  },
  created () {
    if (!this.requsetParams.iscreate) {
      const {
        id, rolecode, rolename, description, resourceids, status
      } = this.requsetParams
      this.id = id
      this.resourceids = resourceids.split(',').map(s => Number.parseInt(s))
      this.formInline = {
        rolecode,
        rolename,
        description,
        status
      }
    }
  }
}
</script>
