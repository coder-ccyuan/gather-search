import {createApp} from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import router from "./router";
import * as Icons from "@ant-design/icons-vue";
import {createPinia} from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import {QuillEditor} from "@vueup/vue-quill";
import '@vueup/vue-quill/dist/vue-quill.snow.css';

const icons = Icons;
const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(Antd);
app.use(router);
app.use(pinia);
app.mount('#app')
for (const i in icons) {
    app.component(i, icons[i]);
}
//注册富文本编辑器组件
app.component('QuillEditor', QuillEditor)
