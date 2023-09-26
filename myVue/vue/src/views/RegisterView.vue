<template>
  <div class="register-container">
    <el-card class="register-card">
      <div class="login-header">
<!--        <img src="@/assets/lottery.jpeg" alt="Logo" class="logo">-->
        <h1 class="title">抽奖系统</h1>
      </div>
      <el-form ref="registerForm" :model="registerForm" label-width="80px" class="register-form">
        <el-form-item label="账号" prop="username">
          <el-input v-model="registerForm.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="registerForm.pwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input type="password" v-model="registerForm.confirmPwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleRegister">注册</el-button>
          <router-link to="/login"><el-button style="margin-left: 70px" class="login-btn">返回登录</el-button></router-link>
        </el-form-item>
      </el-form>
      <div class="register-card-buttons">

      </div>
    </el-card>
  </div>

</template>

<script>
import qs from "qs";
import axios from "axios";

export default {
  data() {
    return {
      registerForm: {
        account: '',
        pwd: '',
        confirmPwd: ''
      }
    };
  },
  methods: {
    handleRegister() {
      // 处理注册逻辑，可以在这里发送注册请求
      const options = {
        methods: 'POST',
        url: '/userManagement/register',
        data: qs.stringify(this.registerForm),
        headers: {'content-type': 'application/x-www-form-urlencoded'},
      }
      // axios(options)

      axios.post('/userManagement/register', qs.stringify(this.registerForm)).then(res => {
        if (res.data.code === '0'){
          this.$notify.success({
            title: '注册成功',
            message: res.data.msg
          });
          this.$router.push('/login');
        } else {
          this.$notify.error({
            title: '注册失败',
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
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.register-card {
  width: 400px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.register-form {
  margin: 20px;
}

.register-card-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 0 20px;
  align-items: center;
}
</style>
