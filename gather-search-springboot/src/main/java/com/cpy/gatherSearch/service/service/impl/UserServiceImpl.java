package com.cpy.gatherSearch.service.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.constants.UserConstant;
import com.cpy.gatherSearch.dao.mapper.UserMapper;

import com.cpy.gatherSearch.model.dto.User.UserAddRequest;
import com.cpy.gatherSearch.model.dto.User.UserQueryRequest;
import com.cpy.gatherSearch.model.dto.User.UserRegisterRequest;
import com.cpy.gatherSearch.model.dto.User.UserUpdateRequest;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.UserService;
import com.cpy.gatherSearch.utils.ResultUtils;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 成希德
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-22 20:20:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public boolean verify(UserAddRequest addRequest) {
        if(!VerifyUtils.verifyString(addRequest.getUserAccount())||!VerifyUtils.verifyString(addRequest.getUserPassword())){
            return false;
        }
        if (addRequest.getUserAccount().length()>10||addRequest.getUserAccount().length() < 4 || addRequest.getUserPassword().length() < 8 ) {
            return false;
        }
        Matcher matcher = Pattern.compile("[0-9A-Za-z]{8,16}").matcher(addRequest.getUserPassword());
        if (!matcher.find()) {
            return false;
        }
        //查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", addRequest.getUserAccount());
        int count = this.count(userQueryWrapper);
        if(count>=1)return false;
        //加密
        String MD5Password = DigestUtils.md5DigestAsHex((UserConstant.SALT +addRequest.getUserPassword()).getBytes());
        User user = new User();
        user.setUserAccount(addRequest.getUserAccount());
        user.setUserPassword(MD5Password);
        user.setUserName("用户"+addRequest.getUserAccount());
        user.setUserAvatar("/images/images.jpg");
        user.setUserProfile("该用户没有简介");
        boolean save = this.save(user);
        return save;
    }

    @Override
    public boolean verify(UserUpdateRequest updateRequest) {
        Long id = updateRequest.getId();
        String unionId = updateRequest.getUnionId();
        String mpOpenId = updateRequest.getMpOpenId();
        String userName = updateRequest.getUserName();
        String userAvatar = updateRequest.getUserAvatar();
        String userProfile = updateRequest.getUserProfile();
        String userRole = updateRequest.getUserRole();
        Integer isDelete = updateRequest.getIsDelete();
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setUserAvatar(userAvatar);
        user.setUserProfile(userProfile);
        user.setIsDelete(isDelete);
        boolean flag = this.updateById(user);
        return flag;
    }

    @Override
    public User getSafeUser(User user) {
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setUnionId(user.getUnionId());
        safeUser.setMpOpenId(user.getMpOpenId());
        safeUser.setUserName(user.getUserName());
        safeUser.setUserAvatar(user.getUserAvatar());
        safeUser.setUserProfile(user.getUserProfile());
        safeUser.setUserRole(user.getUserRole());
        safeUser.setCreateTime(user.getCreateTime());
        safeUser.setUpdateTime(user.getUpdateTime());
        return safeUser;

    }

    @Override
    public boolean registerUser(UserRegisterRequest registerRequest) {
        if(!VerifyUtils.verifyString(registerRequest.getUserAccount())||!VerifyUtils.verifyString(registerRequest.getUserPassword())
        || !VerifyUtils.verifyString(registerRequest.getCheckPassword())){
            return false;
        }
        if (registerRequest.getUserAccount().length() < 4 || registerRequest.getUserPassword().length() < 8 || !registerRequest.getUserPassword().equals(registerRequest.getCheckPassword())) {
            return false;
        }
        Matcher matcher = Pattern.compile("[0-9A-Za-z]{8,16}").matcher(registerRequest.getUserPassword());
        if (!matcher.find()) {
            return false;
        }
        //查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", registerRequest.getUserAccount());
        int count = this.count(userQueryWrapper);
        if(count>=1)return false;
        //加密
        String MD5Password = DigestUtils.md5DigestAsHex((UserConstant.SALT +registerRequest.getUserPassword()).getBytes());
        User user = new User();
        user.setUserAccount(registerRequest.getUserAccount());
        user.setUserPassword(MD5Password);
        user.setUserName("用户"+registerRequest.getUserAccount());
        user.setUserAvatar("/images/images.jpg");
        user.setUserProfile("该用户没有简介");
        boolean save = this.save(user);
        return save;
    }

    @Override
    public List<User> getUserList(UserQueryRequest queryRequest) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (queryRequest == null || (!VerifyUtils.verifyString(queryRequest.getUserName()) && queryRequest.getUserAccount() == null)) {
            return this.list(qw);
        }
        qw.like("userAccount", queryRequest.getUserAccount()).or().like("id", queryRequest.getId()).or().like("userName", queryRequest.getUserName());
        //执行查询
        List<User> list = this.list(qw);
        return list;
    }
}




