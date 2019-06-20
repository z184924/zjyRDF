<template>
  <div>
    <el-container>
      <el-header style="background-color:#67C23A;height:60px;">
        <div style="margin-top:15px;font-size:20px;color:#FFF">
          Vue
        </div>
      </el-header>
      <el-main
        style="background-color:#EBEEF5"
        v-bind:style="{height:mainHeight+'px'}"
      >
        <div style="margin-top:1%;background-color:#FFF; width:30%;margin-left:33%;border:2px solid #DCDFE6;border-radius:25px; padding:3%">
          <div style="text-align:center;margin-bottom:5%">
            <img
              src="../assets/logo.png"
              style="width:100px"
            >
          </div>
          <div style="text-align:center;margin-bottom:5%;font-size:20px">
            登&nbsp;&nbsp;&nbsp;&nbsp;录
          </div>
          <div style="width:80%;margin-left:13%;">
            <el-input
              v-model="account"
              placeholder="账号"
              style="width:90%"
              @keypress.enter.native="login"
            >
              <template slot="prepend"><span class="iconfont">&#xe6b8;</span></template>
            </el-input>
          </div>
          <br>
          <div style="width:80%;margin-left:13%;">
            <el-input
              type="password"
              v-model="password"
              placeholder="密码"
              style="width:90%"
              @keypress.enter.native="login"
            >
              <template slot="prepend"><span class="iconfont">&#xe82b;</span></template>
            </el-input>
          </div>
          <br>
          <div style="width:70%;margin-left:14%;">
            <el-button
              type="success"
              style="width:100%"
              @click="login"
            >登录</el-button>
            <input
              style="padding:0px;border:0; width:0px;height:0px"
              ref="loginInput"
              @keypress.enter="login"
              autofocus
            >
          </div>
        </div>
      </el-main>
      <el-footer style="background-color:#909399;height:60px;">
        <div style="margin-top:15px;text-align:center;font-size:20px;color:#606266">
          Powered by zjy
        </div>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
import $ from "jquery"
export default {
  data() {
    return {
      account: "",
      password: "",
      screenWidth: document.body.clientWidth,
      mainHeight: $(window).height() - 60 - 60 - 16,
    }
  },
  methods: {
    login() {
      this.mixLogin(this.account, this.password).then(() => {
        this.$router.replace("/index");
      }).catch(() => { })
    },
  },
  mounted() {
    this.account = localStorage.account;
    this.password = localStorage.password;
    const that = this
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth
        this.mainHeight = $(window).height() - 60 - 60 - 16
        that.screenWidth = window.screenWidth
      })()
    }
  },
}
</script>
<style>
@font-face {
  font-family: "iconfont";
  src: url("../../static/font/iconfont.eot");
  src: url("../../static/font/iconfont.eot?#iefix") format("embedded-opentype"),
    url("../../static/font/iconfont.woff2") format("woff2"),
    url("../../static/font/iconfont.woff") format("woff"),
    url("../../static/font/iconfont.ttf") format("truetype"),
    url("../../static/font/iconfont.svg#iconfont") format("svg");
}
.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
