 import {defineStore} from 'pinia'
 export const useUserStore=defineStore('user',{
     // 动作
     actions:{
         clearUser(){
             this.user={}
             this.isLogin=false;
         }
     },
     // 状态
     state(){
         return {
             user:{
             },
             isLogin:false
         }
     },
     // 计算
     getters:{},
     // persist:true
     persist: {
         key: 'userStore', //存储名称
         storage: sessionStorage, // 存储方式
         paths: ['user','isLogin'], //指定 state 中哪些数据需要被持久化。[] 表示不持久化任何状态，undefined 或 null 表示持久化整个 state
     },
 })