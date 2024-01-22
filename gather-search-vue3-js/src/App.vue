<template id="app">
  <div>
    <router-view/>
  </div>

</template>

<script>


export default {
  name: 'App',
  components: {

  }
}
</script>

<style>
#app {
  color: #2c3e50;
  padding: 20px;
  //max-width: 1024px;
  margin: 0 auto;
  background-color: white;
}
a {
  text-decoration: none; /* 去除默认的下划线 */
  outline: none;	/* 去除旧版浏览器的点击后的外虚线框 */
  color: #1097ef;	/* 去除默认的颜色和点击后变化的颜色 */
}
</style>
