<template>
  <a-input size="large" :bordered="false" placeholder="请输入标题" v-model:value="title"></a-input>
  <QuillEditor theme="snow" content-type='html' v-model:content="content" @blur="paperStore.save(content,title)"/>
  <a-button type="default" style="width: 200px;margin: 10px " @click="paperStore.save(content,title)">保存</a-button>
  <a-button type="primary" style="width: 200px;margin: 10px" @click="addPaper">发布</a-button>
</template>
<script setup>
import {ref, watch} from "vue";
import {usePaperStore} from "@/store/paperStore";
import postApi from "@/api/postApi";
import {useUserStore} from "@/store/userStore";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";

const paperStore = usePaperStore();
const content = ref(paperStore.content)
const title=ref(paperStore.title);
const user=useUserStore().user;
const router=useRouter();
const addPaper=()=>{
  postApi().addPaper({
    userId: user.id,
    title: title.value,
    content: content.value
  }).then((res)=>{
    if (res.code===20000){
      message.success("发布成功")
      title.value="";
      content.value="";
      paperStore.save("","");
      router.go(0)
    }
  })
}
watch([title,content],()=>{
  paperStore.save(content.value,title.value);
})
</script>
<style scoped>
.header {
  background-color: white;
}

</style>