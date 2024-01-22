
import axios from "@/plugins/axios";


export default function () {
    let data=[];

    // 方法
    async function getPostList(searchValue) {
        try {
            // 发请求
            // 维护数据
            data=(await axios.get('/post/list/context', {
                params: {
                    context: searchValue
                }
            }));
        } catch (error) {
            // 处理错误
                console.log(error.message)
        }
        return data;
    }
    async function addPaper(paper) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.post('/post/add', {
                title: paper.title,
                userId: paper.userId,
                content: paper.content
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data;
    }
    async function deletePaper(id) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.post('/post/delete', {
               id:id
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data;
    }
    async function getPostByUsrId(userId) {
        try {
            // 发请求
            // 维护数据
            data=(await axios.get('/post/getByUserId', {
                params: {
                   userId:userId
                }
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data;
    }
    //向外部暴露数据
    return {getPostList,addPaper,deletePaper,getPostByUsrId}
}
