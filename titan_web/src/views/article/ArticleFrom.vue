<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="标题">
        <Input v-model="formInline.title"/>
      </FormItem>
      <FormItem label="内容">
        <QuillEditor v-model="formInline.content" :height="height"/>
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
import QuillEditor from '../../components/VueQuillEditor'

export default {
  name: 'article-from',
  data () {
    const {id, title, content, status} = this.$route.params
    return {
      id,
      height: 500,
      formInline: {
        title,
        content,
        status
      }
    }
  },
  components: {
    QuillEditor
  },
  methods: {
    ...mapActions('article', [
      'editDatas',
      'createData'
    ]),
    handleSubmit () {
      if (this.$route.params.iscreate) {
        this.createData(this.formInline).then(response => {
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
          'params': this.formInline
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
  }
}
</script>
