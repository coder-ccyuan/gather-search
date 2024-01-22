<script setup>
<<<<<<< HEAD
import {defineProps} from 'vue'
=======
import {defineProps, onMounted} from 'vue'
>>>>>>> c29c9f8 (perfect)
import {useRoute, useRouter} from "vue-router";
import postApi from "@/api/postApi";
import {message} from "ant-design-vue";

defineProps(["postList"]);
const route = useRoute();
<<<<<<< HEAD
const router=useRouter()
=======
const router=useRouter();
>>>>>>> c29c9f8 (perfect)
const deletePaper = (id) => {
  postApi().deletePaper(id).then(res => {
    if (res.code === 20000) {
      message.success("删除成功");
      router.go(0);
      // postList.value = postList.value.filter(item => item.id !== id);
    }
  })
}
<<<<<<< HEAD
=======
onMounted(()=>{

})
>>>>>>> c29c9f8 (perfect)
</script>
<template>
  <a-list item-layout="horizontal" :data-source="postList">
    <template #renderItem="{ item }">
<<<<<<< HEAD
      <a-list-item>
        <a-list-item-meta
            :description="item.content.slice(0,40) "
        >
          <template #avatar>
            <a-avatar src="http://localhost:8081/images/images.jpg"/>
          </template>
=======
      <a-avatar :src="item.userAvatar"/>
      {{item.userName}}
      <a-list-item>
        <a-list-item-meta>
          <!--          <template #avatar>-->
          <!--            <a-avatar src="http://localhost:8081/images/images.jpg"/>-->
          <!--          </template>-->
>>>>>>> c29c9f8 (perfect)
          <template #title>
            <RouterLink :to="{
                  path:'/paperDisplay',
                  query:{
                  title:item.title,
                  content:item.content,
                  userId: item.userId,
                  createTime: item.createTime,
<<<<<<< HEAD
                  updateTime: item.updateTime
=======
                  updateTime: item.updateTime,
                  userName: item.userName,
                  userAvatar:item.userAvatar
>>>>>>> c29c9f8 (perfect)
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