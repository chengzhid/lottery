<template>
  <div>
    <el-container>
      <el-header>
        <el-dropdown style="float: right; height: 60px; line-height: 60px">
<span class="el-dropdown-link" style="color: black; font-size: 16px">{{ user.userAccount }}<i
    class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="logout">退出登录</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
<!--        <el-button type="warning" @click="deleteLocalStorage">-->
<!--          退出登录-->
<!--        </el-button>-->
      </el-header>
      <el-main>
        <el-card class="lottery-card">

          <div>
            <el-table :data="tableData" style="width: 100%; margin: auto">
              <el-table-column prop="prizeName" label="奖品名" width="300"></el-table-column>
              <el-table-column prop="prizeGrade" label="奖品等级" width="300"></el-table-column>
              <el-table-column prop="prizeAmount" label="奖品剩余数量"></el-table-column>
            </el-table>
          </div>
          <div style="margin: 20px auto; width: 100px; height: 50px">
<!--            <h1>{{user.userAccount}}</h1>-->
<!--            <h1>{{user.userAccount}}的抽奖历史记录：{{}}</h1>-->
            <el-button type="primary" @click="getLottery" round>抽奖</el-button>
          </div>

        </el-card>
      </el-main>
    </el-container>

  </div>



</template>

<script>
import qs from "qs";
import axios from "axios";

export default {
  created() {
    this.load();
  },
  methods:{
    // getHistoryLottery(){
    //   const options = {
    //     methods: 'POST',
    //     url: '/lottery/getMyHistoryPrize',
    //     // data: qs.stringify(this.loginForm),
    //     headers: {'content-type': 'application/x-www-form-urlencoded'},
    //   }
    //
    //   axios.post('/getMyHistoryPrize', qs.stringify(this.user)).then(res => {
    //     if (res.data.code === '0'){
    //       this.load();
    //       this.tableData = res.data.data;
    //       this.$notify.success({
    //         title: '抽奖成功',
    //         message: res.data.data
    //       });
    //     } else {
    //       this.$notify.error({
    //         title: '抽奖失败',
    //         message: res.data.msg
    //       });
    //     }
    //   })
    // },
    getLottery(){
      const options = {
        methods: 'POST',
        url: '/lottery/getAllPrizeInfo',
        // data: qs.stringify(this.loginForm),
        headers: {'content-type': 'application/x-www-form-urlencoded'},
      }

      axios.post('/getLottery', qs.stringify(this.user)).then(res => {
        if (res.data.code === '0'){
          this.load();
          this.tableData = res.data.data;
          this.$notify.success({
            title: '抽奖成功',
            message: res.data.data
          });
        } else {
          this.$notify.error({
            title: '抽奖失败',
            message: res.data.msg
          });
        }
      })
    },
    logout(){
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
    load(){
      //alert('页面一加载，就会弹出此窗口')
      const options = {
        methods: 'GET',
        url: '/lottery/getAllPrizeInfo',
        // data: qs.stringify(this.loginForm),
        headers: {'content-type': 'application/x-www-form-urlencoded'},
      }
      // axios(options)

      axios.get('/getAllPrizeInfo').then(res => {
        if (res.data.code === '0'){
          this.tableData = res.data.data;
        } else {
          this.$notify.error({
            title: '获取奖品信息失败',
            message: res.data.msg
          });
        }
      })
    }
  },
  data() {

    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      // history: localStorage.getItem("history") ? JSON.parse(localStorage.getItem("history")) : {},
      tableData: [{
        prizeName: '',
        prizeGrade: '',
        prizeAmount: ''
      }],
      getLotteryUserData: [{
        // userId: this.user.userId,
        userId: ''
      }]
    }
  }
}
</script>

<style scoped>
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
.lottery-card{
  width: 1000px;
  margin: auto;
}
</style>
