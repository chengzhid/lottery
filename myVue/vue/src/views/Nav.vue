<template>
  <el-aside style="overflow: hidden; min-height: 100vh; background-color: #545c64; width:250px">
    <el-menu :default-active="$route.path" router background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
      <el-menu-item index="/index">
        <i class="el-icon-s-home"></i>
        <span slot="title">系统首页</span>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i><span>用户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/admin">我的信息</el-menu-item>
          <!--              <el-menu-item index="/user">用户信息</el-menu-item>-->
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i><span>抽奖项目</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="3-1">全部项目</el-menu-item>
          <el-menu-item index="3-1">我创建的项目</el-menu-item>
          <el-menu-item index="3-2">我参加的项目</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-location"></i><span>通知消息</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="4-1">我的消息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: "Nav"
}
</script>

<style scoped>

</style>