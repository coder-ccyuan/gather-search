import axios from "@/plugins/axios";

export default function () {
    let data=[];
    async function getUserList(searchValue) {
        try {
            // 发请求
            // 维护数据
            data=(await axios.get('/user/query', {
                params: {
                    userName: searchValue
                }
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data
    }

    async function login(loginP) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.post('/user/login', {
                "userAccount": loginP.userAccount,
                "userPassword": loginP.userPassword
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data
    }
    async function register(registerP) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.post('/user/register', {
                "userAccount": registerP.userAccount,
                "userPassword": registerP.userPassword,
                "checkPassword": registerP.checkPassword
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data
    }
    async function logout() {
        try {
            // 发请求
            // 维护数据
            data = (await axios.get('/user/logout'));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data
    }
    async function update(up) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.post('/user/update',{
                "id": up.id,
                "userName": up.userName,
                "userAvatar": up.userAvatar,
                "userProfile": up.userProfile,
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data
    }

    async function getUserById(id) {
        try {
            // 发请求
            // 维护数据
            data = (await axios.get('/user/getById', {
                params: {
                    id: id
                }
            }));
        } catch (error) {
            // 处理错误
            console.log(error.message)
        }
        return data;
    }
    //向外部暴露数据
    return {getUserList,login,logout,update,getUserById,register}
}
