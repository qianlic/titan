<template>
  <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
    <FormItem label="名称">
      <Input v-model="formInline.name" :disabled="!requsetParams.iscreate"/>
    </FormItem>
    <FormItem label="所属组">
      <Input v-model="formInline.group" :disabled="!requsetParams.iscreate"/>
    </FormItem>
    <FormItem label="服务">
      <Input v-model="formInline.server"/>
    </FormItem>
    <FormItem label="路径">
      <Input v-model="formInline.path"/>
    </FormItem>
    <FormItem label="参数">
      <Input v-model="formInline.data"/>
    </FormItem>
    <FormItem label="优先级">
      <Input v-model="formInline.priority"/>
    </FormItem>
    <FormItem label="CRON表达式">
      <Input v-model="formInline.cronExpression"/>
    </FormItem>
    <FormItem label="描述">
      <Input v-model="formInline.description" type="textarea"/>
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
  name: 'schedule-from',
  data () {
    return {
      formInline: {}
    }
  },
  computed: {
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('schedule', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      let method = this.createData
      if (!this.requsetParams.iscreate) {
        method = this.editDatas
      }
      method(this.formInline).then(response => {
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
        name, group, server, path, data, priority, type, cronExpression, description
      } = this.requsetParams
      this.formInline = {
        name,
        group,
        server,
        path,
        data,
        priority,
        type,
        cronExpression,
        description
      }
    }
  }
}
</script>
