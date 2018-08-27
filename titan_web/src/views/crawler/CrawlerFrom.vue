<template>
  <div>
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
  </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: 'crawler-from',
  data () {
    const {id, name, resumable, seed, number, status} = this.$route.params
    return {
      formInline: {
        id,
        name,
        resumable,
        seed,
        number,
        status
      }
    }
  },
  methods: {
    ...mapActions('crawler', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      const params = this.formInline
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
        this.editDatas(params).then(response => {
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
  }
}
</script>
