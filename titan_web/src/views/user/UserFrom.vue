<template>
  <div>
    <CropperModal v-model="isShowImageModle" :imgurl="formInline.imgurl" @on-submit="hhsuccess"/>
    <Form :model="formInline" label-position="left" :label-width="100" style="margin: 10px">
      <FormItem label="用户头像">
        <div class="preview" @click="isShowImageModle = true">
          <img :src="formInline.imgurl" width="200px"/>
          <div class="mask">
            <h3>编 辑 头 像</h3>
          </div>
        </div>
      </FormItem>
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
import PageModal from '../../components/modal/PageModal'
import CropperModal from '../../components/modal/CropperModal'
import {formatDate} from '../../utils'

export default {
  name: 'user-from',
  data () {
    const {id, usercode, username, sex, birthday, mobile, email, imgurl, status} = this.$route.params
    return {
      indeterminate: false,
      checkAll: false,
      isShowImageModle: false,
      roleids: [],
      id,
      formInline: {
        imgurl,
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
  components: {
    PageModal,
    CropperModal
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
    hhsuccess (response) {
      if (response.status === 0) {
        this.formInline.imgurl = response.data.url
        this.isShowImageModle = false
        this.$Message.success(response.message)
      } else {
        this.$Message.error(response.message)
      }
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

<style scoped>
  .preview{
    overflow: hidden;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background: #cccccc;
    cursor: pointer;
    position: relative;
  }

  .mask {
    position: absolute;
    top: 0;
    left: 0;
    width: 200px;
    height: 200px;
    background: rgba(101, 101, 101, 0.6);
    color: #ffffff;
    opacity: 0;
  }
  .mask h3 {
    line-height: 200px;
    text-align: center;
  }

  .preview:hover .mask {
    opacity: 1;
  }
</style>
