<script setup>
import {useUserStore} from "@/store/userStore";
import userApi from "@/api/userApi";
import {message} from "ant-design-vue";
import {useRoute, useRouter} from "vue-router";

const userStore=useUserStore()
const user=userStore.user;
const router=useRouter()
const route=useRoute();
const logout=()=>{
  userApi().logout().then(res=>{
    if (res.code===20000){
      //清除sessionStorage
      userStore.clearUser();

      message.success("登出成功")
      router.push('/paper')
    }else{
      message.error('登出失败')
    }
  })
}
const toUserCenter=()=>{
  router.push('/userCenter')
}
</script>

<template>
  <!--  github 仓库-->
  <div class="logo" v-if="route.path==='/'||route.path==='/index'||route.path==='/paper'||
route.path==='/picture'||route.path==='/user'">
    <a href="https://github.com/coder-ccyuan/gather-search">
      <GithubOutlined style="font-size: 30px"/>
      GitHub</a>
  </div>
  <!--  返回按钮-->
  <div v-else style="float: left">
    <a-button style="" type="default" @click="router.push('/paper')">回到搜索</a-button>
    <a-button style="" type="default" @click="router.go(-1)">
      <LeftOutlined/>
      返回
    </a-button>
  </div>
  <span style="font-size: 20px; font-style: italic ;margin: 0 30%;color: #4eb2f1">小黑子聚合搜索论坛</span>
  <!-- 未登录状态-->
  <div class="user" v-if="!userStore.isLogin" >
    <a-space>
      <router-link to="/login">
        <span style="font-size: large">点击登录</span>
      </router-link>
      <a-avatar src="http://localhost:8081/images/images.jpg" size="large" alt="点击登录"/>
    </a-space>
  </div>
  <!--  用户下拉菜单 登录-->
  <div class="user" v-if="userStore.isLogin">
    <a-space>
      <!--用户名-->
      <span style="  font-family:Arial,Helvetica,sans-serif;font-size: large">{{ user.userName }}</span>
      <a-dropdown>
        <template #overlay>
          <a-menu>
            <a-menu-item key="userCenter" @click="toUserCenter">
              用户中心
            </a-menu-item>
            <a-menu-item key="userLogout" @click="logout">
              登出
            </a-menu-item>
          </a-menu>
        </template>
        <a-avatar :src="user.userAvatar" size="large"/>
      </a-dropdown>
    </a-space>
  </div>
</template>

<style scoped>
div.user {
  display: inline-block;
  float: right;
  margin-bottom: 5px;
}
div.logo{
  display: inline-block;
}
a{
  color: black;
}
</style>