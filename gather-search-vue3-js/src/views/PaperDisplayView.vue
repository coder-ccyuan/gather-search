<script setup>
import {useRoute} from "vue-router";
<<<<<<< HEAD
import {onMounted, ref} from "vue";
import userApi from "@/api/userApi";
=======
>>>>>>> c29c9f8 (perfect)
import HeadView from "@/views/HeadView.vue";

const route = useRoute();
const paper = route.query;
<<<<<<< HEAD
const user=ref({})
onMounted(()=>{
userApi().getUserById(paper.userId).then(res=>{
  user.value=res.data
})
})
=======
>>>>>>> c29c9f8 (perfect)
</script>

<template>
  <HeadView/>
  <a-divider/>
  <h1 style="text-align: center">{{ paper.title }}</h1>
  <span>
<<<<<<< HEAD
    <a-avatar :src="user.userAvatar"></a-avatar>
    作者：{{user.userName}}</span>
  <div v-html="paper.content" class="content"></div>
  <a-divider/>
  <span>发布时间：{{paper.createTime}}</span>
  <br>
  <span>更新时间：{{paper.updateTime}}</span>
</template>

<style scoped>
.content{
margin: 10px auto;
=======
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
>>>>>>> c29c9f8 (perfect)
  max-width: 1024px;

}
</style>