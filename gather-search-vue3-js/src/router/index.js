import { createRouter, createWebHashHistory } from "vue-router";
import IndexView from "@/views/IndexView.vue";
import TestView from "@/views/TestView.vue";
import LoginView from "@/views/LoginView.vue";
import UserIndexView from "@/views/userCenter/UserIndexView.vue";
import UserMesView from "@/views/userCenter/UserMesView.vue";
import ManagePaperView from "@/views/userCenter/ManagePaperView.vue";
import ExportPaperView from "@/views/userCenter/ExportPaperView.vue";
import PaperDisplayView from "@/views/PaperDisplayView.vue";
import RegisterView from "@/views/RegisterView.vue";
import {useUserStore} from "@/store/userStore";

const routes = [
    {
        path: '/',
        component: IndexView
    },
    {
        path: '/:category',
        name: 'index',
        component: IndexView
    },
    {
        path: '/test',
        name: 'test',
        component: TestView
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterView
    },
    {
        path: '/paperDisplay',
        name: 'paperDisplay',
        component: PaperDisplayView
    },
    {
        path: '/userCenter',
        name: 'userCenter',
        component: UserIndexView,
        children:[
            {
                name:'userMes',
                path:'/userMes',
                component:UserMesView
            },
            {
                name:'managePaper',
                path:'/managePaper',
                component:ManagePaperView
            },
            {
                name:'exportPaper',
                path:'/exportPaper',
                component:ExportPaperView
            },
        ]
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});
/**
 * 路由守卫
 */
router.beforeEach((to,from,next)=>{
    console.log('beforeEach',to,from)
    console.log(useUserStore().isLogin)
    if(!useUserStore().isLogin){ //判断当前路由是否需要进行权限控制
      if (to.path!=='/userCenter'&&to.path!=='/userMes'&&to.path!=='/managePaper'&&to.path!=='/exportPaper'){
          next();
      }else {
          alert("请登录")
      }

    }else{
        next() //放行
    }
})
export default router;