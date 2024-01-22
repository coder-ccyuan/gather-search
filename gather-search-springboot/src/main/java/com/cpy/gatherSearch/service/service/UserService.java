package com.cpy.gatherSearch.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpy.gatherSearch.model.dto.User.UserAddRequest;
import com.cpy.gatherSearch.model.dto.User.UserQueryRequest;
import com.cpy.gatherSearch.model.dto.User.UserRegisterRequest;
import com.cpy.gatherSearch.model.dto.User.UserUpdateRequest;
import com.cpy.gatherSearch.model.entity.User;

import java.util.List;


/**
* @author 成希德
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-22 20:20:58
*/
public interface UserService extends IService<User> {
    /**
     * 添加用户并校验数据
     * @param addRequest
     * @return boolean
     */
    boolean verify(UserAddRequest addRequest);
    /**
     * 更新用户并校验数据
     * @author 成希德
     * @param updateRequest
     * @return boolean
     */
    boolean verify(UserUpdateRequest updateRequest);

    /**
     * 对用户脱敏
     * @param user
     * @return
     */
    User getSafeUser(User user);
    boolean registerUser(UserRegisterRequest registerRequest);
    List<User> getUserList(UserQueryRequest queryRequest);
}
