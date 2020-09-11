<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="标题">
        <Input v-model="formInline.title"/>
      </FormItem>
      <FormItem label="标签">
        <TagGroup v-model="formInline.tags"/>
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
import TagGroup from '../../components/TagGroup'

export default {
  name: 'article-from',
  data () {
    return {
      height: 500,
      id: null,
      formInline: {}
    }
  },
  components: {
    QuillEditor,
    TagGroup
  },
  computed: {
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('article', [
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
        id, tags, title, content, status
      } = this.requsetParams
      this.id = id
      this.formInline = {
        tags,
        title,
        content,
        status
      }
    }
  }
}
</script>
