<script setup>
import {defineProps} from 'vue'

const props = defineProps(["pictureList"])
</script>
<template>
  <template v-for="(item,index) in props.pictureList" :key="index">
    <a-image
        :width="200"
        :src="item.src"
        :alt="item.title"
    />
  </template>
</template>


<style scoped>

</style>