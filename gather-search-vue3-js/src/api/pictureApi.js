import axios from "@/plugins/axios";


export default function () {
    let data = []

    // 方法
    async function getPictureList(searchValue) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.get('/picture/list/query', {
                params: {
                    searchValue: searchValue
                }
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data;
    }

    //向外部暴露数据
    return {getPictureList}
}
