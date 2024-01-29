<script setup>
import {useRoute} from "vue-router";
import HeadView from "@/views/HeadView.vue";

const route = useRoute();
const paper = route.query;
</script>

<template>
  <HeadView/>
  <a-divider/>
  <h1 style="text-align: center">{{ paper.title }}</h1>
  <span>
    <a-avatar :src="paper.userAvatar"></a-avatar>
    作者：{{ paper.userName }}</span>
  <div v-html="paper.content" class="content"></div>
  <a-divider/>
  <span>发布时间：{{ paper.createTime }}</span>
  <br>
  <span>更新时间：{{ paper.updateTime }}</span>
</template>

<style scoped>
.content {
  margin: 10px auto;
  max-width: 1024px;

}
</style>