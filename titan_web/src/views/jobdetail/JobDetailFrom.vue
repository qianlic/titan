<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="任务名称">
        <Input v-model="formInline.name" :disabled="!this.$route.params.iscreate"/>
      </FormItem>
      <FormItem label="所属组">
        <Select v-model="formInline.group" :disabled="!this.$route.params.iscreate">
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
  </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: 'jobdetail-from',
  data () {
    const {name, group, service, method, data, triggerKeys, description} = this.$route.params
    return {
      triggerKeys,
      formInline: {
        name,
        group,
        service,
        method,
        data,
        description
      }
    }
  },
  methods: {
    ...mapActions('jobdetail', [
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
