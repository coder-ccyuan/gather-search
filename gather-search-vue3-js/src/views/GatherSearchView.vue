<script setup>
import {onMounted, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import MyDivider from "@/components/MyDivider.vue";
import PictureList from "@/components/PictureList.vue";
import PostList from "@/components/PostList.vue";
import UserList from "@/components/UserList.vue";
import searchApi from "@/api/searchApi";

const router = useRouter();
const route = useRoute();
const activeKey = ref("");
const userList = ref([]);
const pictureList = ref([]);
const postList = ref([]);
const searchValue = ref([]);

/**
 * 当table改变时
 * @param key
 */
const onTabChange=(key)=>{
  router.push({
    path:`/${key}`,
    query:{
      searchValue:searchValue.value
    }
  })
}
/**
 * 搜索时把搜索栏数据同步到url中
 */
const onSearch=(text)=>{
  searchValue.value=text
  router.push({
    query:{
      searchValue:text
    }
  })
}
/**
 * 加载数据(优化前)
 */
/*const loadDataOld=(text)=>{
  userApi().getUserList(text).then((res)=>{
    userList.value=res.data;
    console.log(userList.value)
  })
  pictureApi().getPictureList(text).then((res)=>{
    pictureList.value=res.data;
  })
  postApi().getPostList(text).then((res)=>{
    postList.value=res.data;
  })
}*/
/**
 * 聚合搜索
 */
const loadData=(text)=>{
  searchApi().getAllData(text).then(res=>{
    let data=res.data;
    userList.value=data.userList;
    postList.value=data.postList;
    pictureList.value=data.pictureList;
    console.log(res.data)
  })
}
/**
 * 监听属性，
 */
watch([searchValue],()=>{
  loadData(searchValue.value)
  console.log("@@",searchValue.value)
})
/**
 * 加载就触发
 */
onMounted(()=>{
  let category = route.params.category;
  if (category===""||category===null){
    activeKey.value="paper"
  }else{
    activeKey.value=category;
  }
  searchValue.value=route.query.searchValue;
  // loadData(searchValue.value)

})
</script>
<template>
  <div class="search">
    <a-input-search
        placeholder="输入搜索内容"
        size="large"
        @search="onSearch"
    />
    <MyDivider/>
    <a-tabs v-model:activeKey="activeKey" @change="onTabChange">
      <a-tab-pane key="paper" tab="文章">
        <PostList :postList="postList"/>
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片" force-render>
        <PictureList :pictureList="pictureList"/>
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">
        <UserList :userList="userList" />
      </a-tab-pane>
    </a-tabs>
  </div >
</template>

<style scoped>
.search{
  max-width: 1024px;
  margin: 0 auto;
}
</style>
