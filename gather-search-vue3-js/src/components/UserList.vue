<script setup>
import {defineProps} from 'vue'

const props = defineProps(['userList']);
// console.log("@@list.userList", props.userList)

</script>

<template>
  <div v-for="item in props.userList" :key="item.id">
    <a-card hoverable style="width: 140px">
      <template #cover>
        <img class="picture" :src="item.userAvatar" style=" height: 140px;margin: auto" alt="">
      </template>
      <a-card-meta :title="item.userName">
        <template #description>{{ item.userProfile }}</template>
      </a-card-meta>
    </a-card>
  </div>

</template>

<style scoped>
div{
  display: inline-block;
  margin: 10px;
}
</style>