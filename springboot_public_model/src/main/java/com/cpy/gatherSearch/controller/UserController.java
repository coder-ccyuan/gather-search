package com.cpy.gatherSearch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.common.DeleteRequest;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.model.dto.User.UserAddRequest;
import com.cpy.gatherSearch.model.dto.User.UserQueryRequest;
import com.cpy.gatherSearch.model.dto.User.UserUpdateRequest;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.UserService;
import com.cpy.gatherSearch.utils.IsUser;
import com.cpy.gatherSearch.utils.ResultUtils;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController//该注解让方法默认返回json
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody UserAddRequest addRequest, HttpServletRequest request){
        //校验数据是否为空
        if(addRequest==null||request==null){
            throw new CommonException(StatuesCode.NULL_ERROR,"请求数据为null");
        }
        //校验具体数据
       User user =userService.verify(addRequest);
        if (user == null) {
            throw new CommonException(StatuesCode.PARAMS_ERROR,"请求参数不符合格式或api名字重复");
        }
        //判断权限
        if (!IsUser.isAdmin(request)) {
            throw new CommonException(StatuesCode.NO_AUTH);
        }

        Boolean save =userService.save(user);
        return ResultUtils.success(save);
    }

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
        return ResultUtils.success(b);
    }
    @GetMapping(value = "/query")
    public BaseResponse<List<User>> delete(@RequestBody UserQueryRequest queryRequest, HttpServletRequest request){
        //校验数据是否为空
        if(queryRequest==null||request==null){
            throw new CommonException(StatuesCode.NULL_ERROR);
        }
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (queryRequest.getId() == null && VerifyUtils.verifyString(queryRequest.getNickname()) && queryRequest.getUsername() == null ) {

            return ResultUtils.success(userService.list(qw));
        }
        qw.like("username", queryRequest.getUsername()).or().like("id", queryRequest.getId()).or().like("nickname", queryRequest.getNickname());

        //执行查询
        List<User> list =userService.list(qw);
        if(list==null){
            throw new CommonException(StatuesCode.PARAMS_ERROR);
        }

        return ResultUtils.success(userService.list(qw));
    }
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody UserUpdateRequest updateRequest, HttpServletRequest request){
        //校验数据是否为空
        if(updateRequest==null||request==null){
            throw new CommonException(StatuesCode.NULL_ERROR,"请求数据为null");
        }
        //校验具体数据
       User user =userService.verify(updateRequest);
        if (user == null) {
            throw new CommonException(StatuesCode.PARAMS_ERROR,"请求参数不符合格式或api名字重复");
        }
        //判断权限
        if (!IsUser.isAdmin(request)) {
            throw new CommonException(StatuesCode.NO_AUTH);
        }

        boolean b =userService.updateById(user);
        return ResultUtils.success(b);
    }
}

