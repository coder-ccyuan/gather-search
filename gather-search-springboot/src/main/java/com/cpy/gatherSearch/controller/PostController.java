package com.cpy.gatherSearch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.common.DeleteRequest;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.model.dto.post.PostAddRequest;
import com.cpy.gatherSearch.model.dto.post.PostQueryRequest;
import com.cpy.gatherSearch.model.dto.post.PostUpdateRequest;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.model.entity.User;
<<<<<<< HEAD
=======
import com.cpy.gatherSearch.model.vo.post.PostVO;
>>>>>>> c29c9f8 (perfect)
import com.cpy.gatherSearch.service.service.PostService;
import com.cpy.gatherSearch.utils.IsUser;
import com.cpy.gatherSearch.utils.ResultUtils;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *帖子接口模块
 */
@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    PostService postService;

    /**
     * 贴子添加
     * @param addRequest
     * @param request
     * @return boolean
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody PostAddRequest addRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (addRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR, "请求数据为null");
        }
        //登录才能操作
        User loginUser = IsUser.getLoginUser(request);
        if (loginUser == null) {
            throw new CommonException(StatuesCode.NO_LOGIN, "没有登录");
        }
        postService.addPost(addRequest,request);
        return ResultUtils.success(true);
    }

    /**
     * 帖子删除（管理员）
     *
     * @param deleteRequest
     * @param request
     * @return boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (deleteRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR);
        }
        if (IsUser.getLoginUser(request)==null){
            throw new CommonException(StatuesCode.NO_AUTH,"请登录");
        }
        boolean b = postService.deletePost(deleteRequest.getId().longValue(), request);
        return ResultUtils.success(true);
    }

    /**
     * 通过帖子标题和内容查询
     * @param queryRequest
     * @param request
     * @return 帖子集合
     */
    @GetMapping(value = "/list/context")
<<<<<<< HEAD
    public BaseResponse<List<Post>> query(PostQueryRequest queryRequest, HttpServletRequest request) {
=======
    public BaseResponse<List<PostVO>> query(PostQueryRequest queryRequest, HttpServletRequest request) {
>>>>>>> c29c9f8 (perfect)
        //校验数据是否为空
        if (request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR);
        }

        return ResultUtils.success(postService.getPostListByEs(queryRequest));
    }

    /**
     * 帖子更新（管理员）
     *
     * @param updateRequest userUpdateDto
     * @param request
     * @return boolean
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody PostUpdateRequest updateRequest, HttpServletRequest request) {
        //校验数据是否为空
        if (updateRequest == null || request == null) {
            throw new CommonException(StatuesCode.NULL_ERROR, "请求数据为null");
        }
        if (!IsUser.isLoginUserOrAdmin(request,updateRequest.getUserId())){
            throw new CommonException(StatuesCode.NO_AUTH,"没有权限更改用户信息");
        }
        boolean b = postService.updatePost(updateRequest);
        if (!b){
            throw new CommonException(StatuesCode.PARAMS_ERROR);
        }
        return ResultUtils.success(true);
    }
    @GetMapping("/getByUserId")
<<<<<<< HEAD
    public BaseResponse<List<Post>> getByUserId(Long userId, HttpServletRequest request) {
=======
    public BaseResponse<List<PostVO>> getByUserId(Long userId, HttpServletRequest request) {
>>>>>>> c29c9f8 (perfect)
        //校验数据是否为空
        if (userId == null || request == null||userId<1) {
            throw new CommonException(StatuesCode.NULL_ERROR, "请求数据为null");
        }
<<<<<<< HEAD
        List<Post> listByUserId = postService.getListByUserId(userId);
=======
        List<PostVO> listByUserId = postService.getListByUserId(userId);
>>>>>>> c29c9f8 (perfect)
        return ResultUtils.success(listByUserId);
    }
}
