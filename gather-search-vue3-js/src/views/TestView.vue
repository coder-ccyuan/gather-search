<template>
    <QuillEditor theme="snow" 	content-type='html' v-model:content="content" @blur="paperStore.save(content)"/>
</template>
<script setup>
import { ref} from "vue";
import {usePaperStore} from "@/store/paperStore";

const paperStore=usePaperStore();
const content=ref(paperStore.content)
</script>
<style scoped>
.header {
  background-color: white;
}
</style>