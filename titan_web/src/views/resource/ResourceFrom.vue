<template>
  <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
    <FormItem label="资源编码">
      <Input v-model="formInline.resourcecode"/>
    </FormItem>
    <FormItem label="资源名称">
      <Input v-model="formInline.resourcename"/>
    </FormItem>
    <FormItem label="类型">
      <Select v-model="formInline.type" :disabled="!requsetParams.iscreate">
        <Option value="1">
          <Icon type="ios-folder-outline"/>
          分 组
        </Option>
        <Option value="2">
          <Icon type="ios-list-outline"/>
          资 源
        </Option>
        <Option value="3">
          <Icon type="ios-list-outline"/>
          权 限
        </Option>
      </Select>
    </FormItem>
    <FormItem label="分组" v-if="formInline.type === '2'">
      <Select v-model="formInline.parentid">
        <Option v-for="option in datas.filter(x => x.type === '1')" :value="option.id" :key="option.id">
          {{option.resourcename}}
        </Option>
      </Select>
    </FormItem>
    <FormItem label="资 源" v-if="formInline.type === '3'">
      <Select v-model="formInline.parentid">
        <Option v-for="option in datas.filter(x => x.type === '2')" :value="option.id" :key="option.id">
          {{option.resourcename}}
        </Option>
      </Select>
    </FormItem>
    <FormItem label="状态">
      <i-switch v-model="formInline.status" size="large">
        <span slot="open">启用</span>
        <span slot="close">禁用</span>
      </i-switch>
    </FormItem>
    <FormItem label="图标" v-if="formInline.type !== '3'">
      <Input v-model="formInline.ico" :icon="formInline.ico"/>
    </FormItem>
    <FormItem label="路径" v-if="formInline.type === '3'">
      <Input v-model="formInline.url" :icon="formInline.url"/>
    </FormItem>
    <FormItem label="描述">
      <Input v-model="formInline.description" type="textarea" placeholder="Enter something..."/>
    </FormItem>
    <FormItem>
      <Button type="primary" @click="handleSubmit">提交</Button>
      <Button type="ghost" style="margin-left: 8px" @click="handleCancel">取消</Button>
    </FormItem>
  </Form>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'resource-from',
  data () {
    return {
      id: null,
      formInline: {}
    }
  },
  computed: {
    ...mapGetters('resource', [
      'datas'
    ]),
    requsetParams () {
      return this.$route.params
    }
  },
  methods: {
    ...mapActions('resource', [
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
        id, resourcecode, resourcename, description, status, ico, type, url, level, parentid
      } = this.requsetParams
      this.id = id
      this.formInline = {
        resourcecode,
        resourcename,
        description,
        status,
        ico,
        type,
        url,
        level,
        parentid
      }
    }
  }
}
</script>
