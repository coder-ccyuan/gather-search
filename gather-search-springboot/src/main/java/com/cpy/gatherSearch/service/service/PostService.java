package com.cpy.gatherSearch.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpy.gatherSearch.model.dto.post.PostAddRequest;
import com.cpy.gatherSearch.model.dto.post.PostQueryRequest;
import com.cpy.gatherSearch.model.dto.post.PostUpdateRequest;
import com.cpy.gatherSearch.model.entity.Post;
import javafx.geometry.Pos;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @author 成希德
* @description 针对表【post(帖子)】的数据库操作Service
* @createDate 2024-01-23 20:29:11
*/
public interface PostService extends IService<Post> {
    boolean addPost(PostAddRequest addRequest, HttpServletRequest request);
    boolean updatePost(PostUpdateRequest updateRequest);
    List<Post> getPostList(PostQueryRequest queryRequest);

    boolean saveAndUpdatePostEs(Post post);
    boolean deletePostEs(Long id);

    List<Post> getPostListByEs(PostQueryRequest queryRequest);

    boolean deletePost(Long id, HttpServletRequest request);
    List<Post> getListByUserId(Long id);
}
