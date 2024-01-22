package com.cpy.gatherSearch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.common.DeleteRequest;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.constants.UserConstant;
import com.cpy.gatherSearch.model.dto.User.*;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.UserService;
import com.cpy.gatherSearch.utils.IsUser;
import com.cpy.gatherSearch.utils.ResultUtils;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口模块
 */
@RestController//该注解让方法默认返回json
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 用户添加（管理员）
     *
     * @param addRequest userAddDto
     * @param request
     * @return boolean
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody UserAddRequest addRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (addRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR, "请求数据为null");
        }
        //判断权限
        if (!IsUser.isAdmin(request)) {
            throw new CommonException(StatuesCode.NO_AUTH);
        }
        //校验具体数据
        boolean flag = userService.verify(addRequest);
        if (!flag) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "请求参数不符合格式或api名字重复");
        }
        return ResultUtils.success(true);
    }

    /**
     * 用户删除（管理员）
     *
     * @param deleteRequest userDeleteDto
     * @param request
     * @return boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (deleteRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR);
        }
        //校验具体数据
        if (deleteRequest.getId() == null || deleteRequest.getId() < 0) {
            throw new CommonException(StatuesCode.PARAMS_ERROR);
        }
//        判断权限
        if (!IsUser.isAdmin(request)) {
            throw new CommonException(StatuesCode.NO_AUTH);
        }
        //执行删除
        boolean b = userService.removeById(deleteRequest.getId());
        if (!b) {
            throw new CommonException(StatuesCode.PARAMS_ERROR);
        }
        return ResultUtils.success(true);
    }

    /**
     * 用户查询
     *
     * @param queryRequest userQueryDto
     * @param request
     * @return 用户集合
     */
    @GetMapping(value = "/query")
    public BaseResponse<List<User>> query(UserQueryRequest queryRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR);
        }
        return ResultUtils.success(userService.getUserList(queryRequest));
    }

    /**
     * 用户更新（管理员）
     *
     * @param updateRequest userUpdateDto
     * @param request
     * @return boolean
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody UserUpdateRequest updateRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (updateRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR, "请求数据为null");
        }
        if (!IsUser.isLoginUserOrAdmin(request,updateRequest.getId())){
            throw new CommonException(StatuesCode.NO_AUTH,"没有权限更改用户信息");
        }
        //校验具体数据,并修改
        boolean flag = userService.verify(updateRequest);
        if (!flag) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "请求参数不符合格式或api名字重复");
        }

        return ResultUtils.success(true);
    }

    /**
     * 用户登录接口
     *
     * @param loginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {
        if (!VerifyUtils.verifyString(loginRequest.getUserPassword()) || !VerifyUtils.verifyString(loginRequest.getUserAccount())) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "请求参数为空");
        }
        String MD5Password = DigestUtils.md5DigestAsHex((UserConstant.SALT + loginRequest.getUserPassword()).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", loginRequest.getUserAccount())
                .eq("userPassword", MD5Password);
        User user = userService.getOne(userQueryWrapper);
        if (user == null) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "账号或密码错误");
        }
        //放入Session域中
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return ResultUtils.success(userService.getSafeUser(user));
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    public BaseResponse<Boolean> registerUser(@RequestBody UserRegisterRequest registerRequest, HttpServletRequest request) {
        Boolean flag = userService.registerUser(registerRequest);
        if (!flag) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "用户名密码不符合要求");
        }
        return ResultUtils.success(true);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public BaseResponse<Boolean> logout( HttpServletRequest request) {
        if (request==null){
            throw new CommonException(StatuesCode.SYSTEM_EXCEPTION,"tomcat请求解析失败");
        }
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return ResultUtils.success(true);
    }
    @GetMapping("/getById")
    public BaseResponse<User> getById(Long id){
        if (id==null||id<1){
            throw new CommonException(StatuesCode.PARAMS_ERROR);
        }
        User byId = userService.getById(id);
        if (byId==null){
            throw new CommonException(StatuesCode.PARAMS_ERROR,"不存在该用户");
        }
        User safeUser = userService.getSafeUser(byId);
        return ResultUtils.success(safeUser);
    }
}

