<script setup>
import {defineProps} from 'vue'
import {useRoute, useRouter} from "vue-router";
import postApi from "@/api/postApi";
import {message} from "ant-design-vue";

defineProps(["postList"]);
const route = useRoute();
const router=useRouter()
const deletePaper = (id) => {
  postApi().deletePaper(id).then(res => {
    if (res.code === 20000) {
      message.success("删除成功");
      router.go(0);
      // postList.value = postList.value.filter(item => item.id !== id);
    }
  })
}
</script>
<template>
  <a-list item-layout="horizontal" :data-source="postList">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta
            :description="item.content.slice(0,40) "
        >
          <template #avatar>
            <a-avatar src="http://localhost:8081/images/images.jpg"/>
          </template>
          <template #title>
            <RouterLink :to="{
                  path:'/paperDisplay',
                  query:{
                  title:item.title,
                  content:item.content,
                  userId: item.userId,
                  createTime: item.createTime,
                  updateTime: item.updateTime
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