<script setup>
import PostList from "@/components/PostList.vue";
import postApi from "@/api/postApi";
import {onMounted, ref} from "vue";
import {useUserStore} from "@/store/userStore";

const postList=ref([]);
const userid=useUserStore().user.id
onMounted(() => {
  postApi().getPostByUsrId(userid).then(res => {
    postList.value = res.data;
  })
})

</script>

<template>
  <PostList :postList="postList"></PostList>
</template>

<style scoped>

</style>