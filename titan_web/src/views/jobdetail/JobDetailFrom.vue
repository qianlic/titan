<template>
  <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
    <FormItem label="任务名称">
      <Input v-model="formInline.name" :disabled="!requsetParams.iscreate"/>
    </FormItem>
    <FormItem label="所属组">
      <Select v-model="formInline.group" :disabled="!requsetParams.iscreate">
        <Option value="GROUPA">任务组A</Option>
        <Option value="GROUPB">任务组B</Option>
        <Option value="GROUPC">任务组C</Option>
      </Select>
    </FormItem>
    <FormItem label="服务">
      <Input v-model="formInline.service"/>
    </FormItem>
    <FormItem label="方法">
      <Input v-model="formInline.method"/>
    </FormItem>
    <FormItem label="数据">
      <Input v-model="formInline.data" type="textarea"/>
    </FormItem>
    <FormItem label="触发器" v-if="triggerKeys.length>0">
      <Card>
        <Checkbox value="true" v-for="(item,idx) in triggerKeys" :key="idx">
          {{ item.name }}
        </Checkbox>
      </Card>
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
  name: 'jobdetail-from',
  data () {
    return {
      triggerKeys: [],
      formInline: {}
    }
  },
  computed: {
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('jobdetail', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      let method = this.createData
      let params = {
        'triggerKeys': this.triggerKeys,
        ...this.formInline
      }
      if (!this.requsetParams.iscreate) {
        method = this.editDatas
      }
      method(params).then(response => {
        if (response.status === 0) {
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
      const {name, group, service, method, data, triggerKeys, description} = this.requsetParams
      this.triggerKeys = triggerKeys
      this.formInline = {
        name,
        group,
        service,
        method,
        data,
        description
      }
    }
  }
}
</script>
