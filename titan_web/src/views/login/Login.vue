<template>
  <div class="context">
    <div class="head_box">
      <div class="inner wrp"></div>
    </div>
    <div class="banner">
      <div class="inner wrp">
        <div class="login_frame">
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
            <div class="verifycode" v-if="loginFailureMessage">
              <span class="frm_input_box">
                  <input class="frm_input" type="text" v-model="loginform.verifycode">
              </span>
              <img src="">
              <a href="javascript:;">换一张</a>
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
      </div>
    </div>
  </div>
</template>

<script>
import localStore from '../../store/localStore'
import request from '../../api/user'
import {mapActions} from 'vuex'

export default {
  data: () => {
    return {
      rememberMe: false,
      loginform: {
        username: undefined,
        password: undefined,
        verifycode: undefined
      },
      loginFailureMessage: undefined
    }
  },
  methods: {
    ...mapActions('user', [
      'login'
    ]),
    userLogin: function () {
      if (!this.rememberMe) {
        localStore.removeStoreItem('REMEMBER_ME')
      }
      request.login(this.loginform).then(response => {
        if (response.status !== 0) {
          this.loginFailureMessage = response.data
        } else {
          localStore.setAuthToken({
            'Authorization': response.data
          })
          console.log(this.rememberMe)
          if (this.rememberMe) {
            localStore.setStoreItem('REMEMBER_ME', this.loginform)
          }
          this.$router.push('/')
        }
      })
    }
  },
  created: function () {
    const s = localStore.getStoreItem('REMEMBER_ME')
    if (s) {
      this.loginform = s
      this.rememberMe = true
    }
  }
}
</script>

<style scoped>
  body, h3, dl, dd {
    margin: 0
  }
  body {
    line-height: 1.6;
    font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif;
    color: #222;
    font-size: 14px
  }
  .context .inner {
    height: 60px
  }
  .context .inner.wrp {
    width: 1000px;
    margin-left: auto;
    margin-right: auto
  }
  .context .inner:after {
    content: "\200B";
    display: block;
    height: 0;
    clear: both
  }
  .banner {
    background: -moz-linear-gradient(45deg, #020031 0, #6d3353 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#020031', endColorstr='#6d3353', GradientType=1)
  }
  .banner .inner {
    height: 460px;
    position: relative
  }
  .banner .inner .login_frame {
    width: 316px;
    float: right;
    margin-top: 25px
  }
  .login_frame {
    padding: 25px 35px 20px;
    border-radius: 2px;
    box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.5);
    background-color: #fff
  }
  .login_frame h3 {
    font-size: 20px;
    font-weight: 400;
    font-style: normal
  }
  .login_err_panel {
    color: #e15f63;
    font-size: 13px;
    margin-top: 5px
  }
  .login_input_panel {
    margin-top: 5px
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
    padding: 11px 0;
    vertical-align: middle;
    width: 100%
  }
  textarea::-moz-placeholder, input::-moz-placeholder {
    color: #a3a3a3
  }
  .icon_login {
    position: absolute;
    left: 15px;
    top: 50%;
    margin-top: -11px;
    width: 16px;
    height: 18px;
    vertical-align: middle;
    display: inline-block
  }
  .icon_login.un {
    background: url("../../assets/login_ico.png") 0 0 no-repeat
  }
  .icon_login.pwd {
    background: url("../../assets/login_ico.png") 0 -28px no-repeat
  }
  .verifycode {
    margin-top: 10px
  }
  .verifycode a {
    margin-left: 3px
  }
  .verifycode img {
    height: 50px;
    vertical-align: middle
  }
  .frm_input_box {
    display: inline-block;
    position: relative;
    height: 50px;
    line-height: 50px;
    vertical-align: middle;
    width: 100px;
    font-size: 16px;
    padding: 0 10px;
    border: 1px solid #e7e7eb
  }
  .frm_input {
    width: 100%;
    background-color: transparent;
    border: 0;
    outline: 0
  }
  .frm_input {
    height: 42px;
    *line-height: 42px;
    margin: 4px 0
  }
  .icon_checkbox {
    background: url("../../assets/login_ico.png") 0 -56px no-repeat;
    width: 16px;
    height: 16px;
    vertical-align: middle;
    display: inline-block;
    margin-right: 3px;
    margin-top: -2px;
  }
  .selected .icon_checkbox {
    background: url("../../assets/login_ico.png") 0 -82px no-repeat
  }
  .login_help_panel {
    overflow: hidden;
    margin-top: 10px
  }
  .login_help_panel label {
    float: left
  }
  .frm_checkbox {
    position: absolute;
    left: -999em
  }
  .frm_checkbox_label {
    display: inline-block;
    text-align: left;
    cursor: pointer;
    margin-right: 1em
  }
  .login_forget_pwd {
    float: right
  }
  .login_btn_panel {
    margin-top: 20px;
    margin-bottom: 10px
  }
  .btn_login {
    display: inline-block;
    overflow: visible;
    vertical-align: middle;
    text-align: center;
    border-radius: 3px;
    border-width: 1px;
    border-style: solid;
    cursor: pointer;
    background-color: #44b549;
    background-image: linear-gradient(to bottom, #44b549 0, #44b549 100%);
    border-color: #44b549;
    color: #fff;
    height: 33px;
    line-height: 33px;
    width: 120px;
    padding-left: 0;
    padding-right: 0
  }
  .btn_login:hover {
    text-decoration: none
  }
  .btn_login button {
    color: #fff
  }
  .btn_login:hover {
    background-color: #2f9833;
    background-image: linear-gradient(to bottom, #2f9833 0, #2f9833 100%);
    border-color: #2f9833;
    box-shadow: none;
    color: #fff
  }
  input {
    font-family: inherit;
    font-size: 100%;
    margin: 0
  }
  input[type="checkbox"] {
    box-sizing: border-box;
    padding: 0
  }
  input::-moz-focus-inner {
    border: 0;
    padding: 0
  }
  a {
    text-decoration: none
  }
  a:hover {
    text-decoration: underline
  }
</style>
