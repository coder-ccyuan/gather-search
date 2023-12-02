<script lang="js">
import { defineComponent } from "vue/dist/vue";
import { reactive } from "vue";

export default defineComponent({
  name: "SchoolC",
  setup() {
    const obj1 = reactive({ name: "ls" });
    return {
      obj1,
    };
  },
});
</script>

<template>
  <h1>{{ obj1.name }}</h1>
</template>

<style scoped></style>
