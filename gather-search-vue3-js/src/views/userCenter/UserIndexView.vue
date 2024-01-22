
<template>
  <a-layout style="background-color: white">
    <a-layout-header class="header"  style="margin: 0 0">
      <HeadView></HeadView>
    </a-layout-header>
    <a-divider style="margin: 4px"/>
    <a-layout style="">
      <a-layout-sider width="200" style="background: #f4f5f6">
        <a-menu
            v-model:selectedKeys="selectedKeys"
            v-model:openKeys="openKeys"
            mode="inline"
            :style="{ height: '100%', borderRight: 0 }"
        >
          <a-sub-menu key="userCenter">
            <template #title>
              <span>
                <a-avatar :src="user.userAvatar"  alt="用户"/>
                用户中心
              </span>
            </template>
            <a-menu-item key="userMes" @click="goUserMes">用户详情</a-menu-item>
            <a-menu-item key="managePaper" @click="goManagePaper">管理帖子</a-menu-item>
            <a-menu-item key="exportPaper" @click="goExportPaper">发布帖子</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px;background-color: white">
        <div v-show="route.path==='/userCenter'"><span style="font-size: 30px">欢迎来到用户中心</span></div>
        <router-view/>
      </a-layout>
    </a-layout>
  </a-layout>

</template>
<script setup>
import {ref} from 'vue';
import HeadView from "@/views/HeadView.vue";
import {useRoute, useRouter} from "vue-router";
import {useUserStore} from "@/store/userStore";

const openKeys = ref(['userCenter']);
const router=useRouter();
const route=useRoute();
const selectedKeys=ref([''])
const user=useUserStore().user;

const goUserMes = () => {
  router.push('/userMes');
}
const goManagePaper=()=>{
  router.push('/managePaper')
}
const goExportPaper=()=>{
  router.push('/exportPaper')
}
</script>
<style scoped>
.header {
  background-color: white;
}
</style>