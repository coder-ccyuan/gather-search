import axios from "@/plugins/axios";

export default function (){
    let data={};
    async function getAllData(searchValue) {
        try {
            // 发请求
            // 维护数据
            data=(await axios.get('/search/all', {
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
    return {getAllData}
}