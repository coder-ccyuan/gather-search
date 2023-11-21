package com.cpy.gatherSearch.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpy.gatherSearch.dao.mapper.UserMapper;
import com.cpy.gatherSearch.model.dto.User.UserAddRequest;
import com.cpy.gatherSearch.model.dto.User.UserUpdateRequest;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.UserService;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 成希德
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-10 23:30:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
@Resource
UserMapper userMapper;
    @Override
    public User verify(UserAddRequest addRequest) {

        //TODO 校验符合格式
        //校验数据
        if(VerifyUtils.verifyString(addRequest.getPassword())){
            return null;
        }
        if(VerifyUtils.verifyString(addRequest.getNickname())){
            return null;
        }
        if(VerifyUtils.verifyString(addRequest.getUsername())){
            return null;
        }
        //判断name是否重复
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",addRequest.getUsername());
        Integer integer = userMapper.selectCount(qw);
        if (integer>0)return null;
        //返回数据
        User user = new User();
        user.setUsername(addRequest.getUsername());
        user.setPassword(addRequest.getPassword());
        user.setNickname(addRequest.getNickname());
        return user;
    }

    @Override
    public User verify(UserUpdateRequest updateRequest) {
        //TODO 校验符合格式
        //校验数据
        if(VerifyUtils.verifyString(updateRequest.getNickname())){
            return null;
        }
        if(updateRequest.getId()<0||updateRequest.getId()==null) {
            return null;
        }
        //返回数据
        User user = new User();
        user.setId(updateRequest.getId());
        user.setNickname(updateRequest.getNickname());
        user.setPhone(updateRequest.getPhone());
        user.setEmail(updateRequest.getEmail());
        user.setGender(updateRequest.getGender());
        user.setRole(updateRequest.getRole());
        user.setState(updateRequest.getGender());
        user.setNickname(updateRequest.getNickname());
        return user;

    }
}




