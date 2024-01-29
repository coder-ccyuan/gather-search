<script setup>
import {defineProps, onMounted} from 'vue'
import {useRoute, useRouter} from "vue-router";
import postApi from "@/api/postApi";
import {message} from "ant-design-vue";

defineProps(["postList"]);
const route = useRoute();
const router=useRouter();
const deletePaper = (id) => {
  postApi().deletePaper(id).then(res => {
    if (res.code === 20000) {
      message.success("删除成功");
      router.go(0);
      // postList.value = postList.value.filter(item => item.id !== id);
    }
  })
}
onMounted(()=>{

})
</script>
<template>
  <a-list item-layout="horizontal" :data-source="postList">
    <template #renderItem="{ item }">
      <a-avatar :src="item.userAvatar"/>
      {{item.userName}}
      <a-list-item>
        <a-list-item-meta>
          <template #title>
            <RouterLink :to="{
                  path:'/paperDisplay',
                  query:{
                  title:item.title,
                  content:item.content,
                  userId: item.userId,
                  createTime: item.createTime,
                  updateTime: item.updateTime,
                  userName: item.userName,
                  userAvatar:item.userAvatar
                       }}">
              {{ item.title }}
            </RouterLink>
          </template>
        </a-list-item-meta>
        <a-button @click="deletePaper(item.id)" v-if="route.path==='/managePaper'" type="primary" danger><DeleteOutlined /></a-button>
      </a-list-item>
    </template>
  </a-list>

</template>

<style scoped>

</style>