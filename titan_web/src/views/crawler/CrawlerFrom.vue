<template>
  <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
    <FormItem label="名称">
      <Input v-model="formInline.name"/>
    </FormItem>
    <FormItem label="可恢复">
      <i-switch v-model="formInline.resumable" size="large">
        <span slot="open">是</span>
        <span slot="close">否</span>
      </i-switch>
    </FormItem>
    <FormItem label="地址">
      <Input v-model="formInline.seed"/>
    </FormItem>
    <FormItem label="线程数">
      <Input v-model="formInline.number"/>
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
import {mapActions} from 'vuex'

export default {
  name: 'crawler-from',
  data () {
    return {
      id: null,
      formInline: {}
    }
  },
  computed: {
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('crawler', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      let method = this.createData
      let params = this.formInline
      if (!this.requsetParams.iscreate) {
        method = this.editDatas
        params = {
          'id': this.id,
          params
        }
      }
      method(params).then(response => {
        if (response.success) {
          this.$Message.success(response.message)
          this.handleCancel()
        } else {
          this.$Message.error(response.message)
        }
      })
    },
    handleCancel () {
      this.$router.go(-1)
    }
  },
  created () {
    if (!this.requsetParams.iscreate) {
      const {id, name, resumable, seed, number, status} = this.requsetParams
      this.id = id
      this.formInline = {
        id,
        name,
        resumable,
        seed,
        number,
        status
      }
    }
  }
}
</script>
