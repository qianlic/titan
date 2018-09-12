<template>
  <div>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <Upload multiple type="drag" action="/api/system/image/upload" name="file"
              :headers="headers" :on-success="hhsuccess" :on-remove="hhremove">
        <img :src="formInline.imgurl" v-if="formInline.imgurl"/>
        <div style="padding: 20px 0" v-if="!formInline.imgurl">
          <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
          <p>Click or drag files here to upload</p>
        </div>
      </Upload>
      <FormItem label="用户编码">
        <Input v-model="formInline.usercode"/>
      </FormItem>
      <FormItem label="用户名称">
        <Input v-model="formInline.username"/>
      </FormItem>
      <FormItem label="性别">
        <RadioGroup v-model="formInline.sex" size="large">
          <Radio label="M">男</Radio>
          <Radio label="F">女</Radio>
        </RadioGroup>
      </FormItem>
      <FormItem label="生日">
        <Input :value="formInline.birthday|formatDate" type="date" @on-change="changeBirthday"/>
      </FormItem>
      <FormItem label="电话">
        <Input v-model="formInline.mobile"/>
      </FormItem>
      <FormItem label="邮箱">
        <Input v-model="formInline.email" type="email"/>
      </FormItem>
      <FormItem label="角色列表">
        <Card>
          <div style="border-bottom: 1px solid #f4f4f4;padding-bottom:6px;margin-bottom:6px">
            <Checkbox :indeterminate="indeterminate" :value="checkAll" @click.prevent.native="handleCheckAll">系统角色
            </Checkbox>
          </div>
          <CheckboxGroup v-model="roleids" @on-change="checkAllGroupChange">
            <Checkbox v-for="item in availablelist" :key="item.id" :label="item.id">
              {{ item.rolename }}
            </Checkbox>
          </CheckboxGroup>
        </Card>
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
import {mapGetters, mapActions} from 'vuex'
import formatDate from '../../utils/date'
import localStore from '../../store/localStore'

export default {
  name: 'user-from',
  data () {
    const {id, usercode, username, sex, birthday, mobile, email, status} = this.$route.params
    const AUTH_TOKEN = localStore.getAuthTokenItem('Authorization')
    return {
      indeterminate: false,
      checkAll: false,
      roleids: [],
      headers: {
        'Authorization': AUTH_TOKEN
      },
      id,
      formInline: {
        imgurl: 'https://i.loli.net/2018/09/07/5b923f842ecff.jpeg',
        usercode,
        username,
        sex,
        birthday,
        mobile,
        email,
        status
      }
    }
  },
  computed: {
    ...mapGetters('role', [
      'availablelist',
      'availableids',
      'availablelength'
    ])
  },
  methods: {
    ...mapActions('user', [
      'editDatas',
      'createData'
    ]),
    hhsuccess (resp, file, fileList) {
      console.log(resp, file, fileList)
      this.formInline.imgurl = 'https://i.loli.net/' + resp.data.path
    },
    hhremove () {
      console.log('asdadadadada')
    },
    handleSubmit () {
      const params = {
        'roleids': this.roleids.join(','),
        ...this.formInline
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
        this.editDatas({
          'id': this.id,
          params
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
    },
    handleCheckAll () {
      if (this.indeterminate) {
        this.indeterminate = false
      } else {
        this.checkAll = !this.checkAll
      }
      if (this.checkAll) {
        this.roleids = this.availableids
      } else {
        this.roleids = []
      }
    },
    checkAllGroupChange () {
      let count = this.roleids.length
      this.indeterminate = false
      this.checkAll = false
      if (count === this.availablelength) {
        this.checkAll = true
      } else if (count > 0) {
        this.indeterminate = true
      }
    },
    changeBirthday ({target}) {
      this.formInline.birthday = target.valueAsNumber
    }
  },
  filters: {
    formatDate (time) {
      const date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd')
    }
  },
  created () {
    if (this.$route.params.roleids) {
      this.roleids = this.$route.params.roleids
        .split(',').map(roleid => Number.parseInt(roleid))
    }
    this.checkAllGroupChange()
  }
}
</script>
