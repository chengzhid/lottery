<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-header">
<!--        <img src="@/assets/lottery.jpeg" alt="Logo" class="logo">-->
        <h1 class="title">抽奖系统</h1>
      </div>
      <el-form ref="loginForm" :model="loginForm" label-width="80px" class="login-form">
        <el-form-item label="账号" prop="account">
          <el-input v-model="loginForm.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input type="password" v-model="loginForm.pwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin">登录</el-button>
          <router-link to="/register"><el-button style="margin-left: 90px" class="register-btn">注册</el-button></router-link>
<!--          <router-link to="/register">注册</router-link>-->
        </el-form-item>
      </el-form>
      <div class="login-card-buttons">

      </div>
    </el-card>
  </div>

</template>

<script>
import request from "@/utils/request";
import axios from "axios";
import qs from "qs";

export default {
  data() {
    return {
      loginForm: {
        account: '',
        pwd: ''
      }
    };
  },
  methods: {
    handleLogin() {
      // 处理登录逻辑，可以在这里发送登录请求
      const options = {
        methods: 'POST',
        url: '/userManagement/doLogin',
        data: qs.stringify(this.loginForm),
        headers: {'content-type': 'application/x-www-form-urlencoded'},
      }
      // axios(options)

      axios.post('/userManagement/doLogin', qs.stringify(this.loginForm)).then(res => {
        if (res.data.code === '0'){
          localStorage.setItem("user", JSON.stringify(res.data.data));
          this.$notify.success({
            title: '登录成功',
            message: res.data.msg
          });
          this.$router.push('/lottery');
        } else {
          this.$notify.error({
            title: '登录失败',
            message: res.data.msg
          });
        }
      })
    }
  }
};
</script>

<style scoped>
.login-header{
  width: 300px;
  margin: auto;
  text-align: center;
}
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-card {
  width: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.login-form {
  margin: 20px;
}

.login-card-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  align-items: center;
}
</style>
