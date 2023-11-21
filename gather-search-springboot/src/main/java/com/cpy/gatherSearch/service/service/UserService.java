package com.cpy.gatherSearch.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpy.gatherSearch.model.dto.User.UserAddRequest;
import com.cpy.gatherSearch.model.dto.User.UserUpdateRequest;
import com.cpy.gatherSearch.model.entity.User;

/**
* @author 成希德
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-10 23:30:00
*/
public interface UserService extends IService<User> {
    User verify(UserAddRequest addRequest);
    User verify(UserUpdateRequest updateRequest);
}
