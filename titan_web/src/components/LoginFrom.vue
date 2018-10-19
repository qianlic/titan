<template>
  <div class="login_frame" @keyup.enter="userLogin">
    <h3>登录</h3>
    <div class="login_err_panel" v-if="loginFailureMessage">
      {{loginFailureMessage}}
    </div>
    <form class="login_form" action="#" method="post">
      <div class="login_input_panel">
        <div class="login_input">
          <i class="icon_login un"></i>
          <input placeholder="用户名" type="text" v-model="loginform.username">
        </div>
        <div class="login_input">
          <i class="icon_login pwd"></i>
          <input placeholder="密码" type="password" v-model="loginform.password">
        </div>
      </div>
      <div class="verifycode">
        <span class="frm_input_box">
            <input class="frm_input" type="text" v-model="loginform.verifycode">
        </span>
        <img :src="verifyCodeUrl" @click="refreshCode"/>
      </div>
      <div class="login_help_panel">
        <label class="frm_checkbox_label" for="rememberCheck" :class="{selected: rememberMe}">
          <i class="icon_checkbox"></i>
          <input class="frm_checkbox" id="rememberCheck" type="checkbox" v-model="rememberMe">记住帐号
        </label>
        <a class="login_forget_pwd" href="#">无法登录？</a>
      </div>
      <div class="login_btn_panel">
        <div class="btn_login" title="点击登录" @click="userLogin">登录</div>
      </div>
    </form>
  </div>
</template>

<script>
import localStore from '../store/localStore'
import request from '../api/user'
import uuid from 'node-uuid'
import {mapActions} from 'vuex'

export default {
  name: 'login-from',
  data: () => {
    const s = uuid.v1()
    return {
      rememberMe: false,
      loginform: {
        s,
        username: undefined,
        password: undefined,
        verifycode: undefined
      },
      verifyCodeUrl: '/api/secure/captcha?s=' + s,
      loginFailureMessage: undefined
    }
  },
  methods: {
    ...mapActions('user', [
      'login'
    ]),
    userLogin () {
      if (!this.rememberMe) {
        localStore.removeStoreItem('REMEMBER_ME')
      }
      request.login(this.loginform).then(response => {
        if (response.status !== 0) {
          this.loginFailureMessage = response.data
          this.refreshCode()
        } else {
          localStore.setAuthToken({
            'Authorization': response.data
          })
          if (this.rememberMe) {
            localStore.setStoreItem('REMEMBER_ME', this.loginform)
          }
          this.$router.push('/')
        }
      })
    },
    refreshCode () {
      const s = uuid.v1()
      this.loginform.s = s
      this.verifyCodeUrl = '/api/secure/captcha?s=' + s
    }
  },
  created: function () {
    const s = localStore.getStoreItem('REMEMBER_ME')
    if (s) {
      this.loginform.username = s.username
      this.loginform.password = s.password
      this.rememberMe = true
    }
  }
}
</script>

<style scoped>
  .login_frame {
    padding: 25px 35px 20px;
    width: 316px;
    border-radius: 2px;
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
    background-color: #fff
  }
  .login_frame h3 {
    font-size: 20px;
    font-weight: 400
  }
  .login_err_panel {
    color: #e15f63;
    font-size: 13px;
    margin-top: 5px
  }
  .login_input_panel {
    margin-top: 10px
  }
  .login_input {
    position: relative;
    padding: 3px 0 3px 54px;
    border: 1px solid #e7e7eb;
    margin-top: -1px
  }
  .login_input input {
    border: 0;
    outline: 0;
    padding: 11px 0
  }
  .icon_login {
    position: absolute;
    left: 15px;
    top: 12px;
    width: 16px;
    height: 18px
  }
  .icon_login.un {
    background: url("../assets/login_ico.png") 0 0 no-repeat
  }
  .icon_login.pwd {
    background: url("../assets/login_ico.png") 0 -28px no-repeat
  }
  .verifycode {
    margin-top: 10px
  }
  .verifycode img {
    height: 45px;
    width: 125px;
    cursor: pointer;
    vertical-align: middle
  }
  .frm_input_box {
    display: inline-block;
    height: 45px;
    line-height: 45px;
    vertical-align: middle;
    width: 117px;
    font-size: 16px;
    padding: 0 20px;
    border: 1px solid #e7e7eb
  }
  .frm_input {
    background-color: transparent;
    border: 0;
    outline: 0
  }
  .login_help_panel {
    margin-top: 15px
  }
  .frm_checkbox {
    display: none;
  }
  .frm_checkbox_label {
    cursor: pointer
  }
  .icon_checkbox {
    background: url("../assets/login_ico.png") 0 -56px no-repeat;
    width: 16px;
    height: 16px;
    vertical-align: middle;
    display: inline-block;
    margin-right: 3px;
    margin-top: -3px
  }
  .selected .icon_checkbox {
    background: url("../assets/login_ico.png") 0 -82px no-repeat
  }
  .login_forget_pwd {
    float: right
  }
  .login_btn_panel {
    margin: 20px 0 10px 0
  }
  .btn_login {
    background-image: linear-gradient(to bottom, #44b549 0, #44b549 100%);
    border-radius: 3px;
    cursor: pointer;
    text-align: center;
    line-height: 33px;
    color: #fff;
    height: 33px;
    width: 120px
  }
  .btn_login:hover {
    background-image: linear-gradient(to bottom, #2f9833 0, #2f9833 100%)
  }
</style>
