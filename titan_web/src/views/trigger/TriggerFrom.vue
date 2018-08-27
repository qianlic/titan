<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="名称">
        <Input v-model="formInline.name" :disabled="!this.$route.params.iscreate"/>
      </FormItem>
      <FormItem label="所属组">
        <Select v-model="formInline.group" :disabled="!this.$route.params.iscreate">
          <Option value="GROUPA">任务组A</Option>
          <Option value="GROUPB">任务组B</Option>
          <Option value="GROUPC">任务组C</Option>
        </Select>
      </FormItem>
      <FormItem label="优先级">
        <Input v-model="formInline.priority"/>
      </FormItem>
      <FormItem label="CRON表达式">
        <Input v-model="formInline.cronExpression"/>
      </FormItem>
      <FormItem label="执行JOB">
        <Card>
          <RadioGroup v-model="job">
            <Radio  v-for="(item,idx) in jobdetails" :key="idx" :label="JSON.stringify(item)">
              {{ item.name }}
            </Radio>
          </RadioGroup>
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
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'trigger-from',
  data () {
    const {name, group, priority, type, repeatInterval, repeatCount, cronExpression, jobKey, description} = this.$route.params
    return {
      job: jobKey !== undefined ? JSON.stringify(jobKey) : undefined,
      formInline: {
        name,
        group,
        priority,
        type,
        repeatInterval,
        repeatCount,
        cronExpression,
        description
      }
    }
  },
  computed: {
    ...mapGetters('jobdetail', [
      'availablelist'
    ]),
    jobdetails () {
      return this.availablelist.filter(job => job.group === this.formInline.group)
    }
  },
  methods: {
    ...mapActions('trigger', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      const params = {
        ...this.formInline,
        jobKey: JSON.parse(this.job)
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
