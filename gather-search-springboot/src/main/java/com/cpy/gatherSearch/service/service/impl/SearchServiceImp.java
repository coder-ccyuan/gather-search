package com.cpy.gatherSearch.service.service.impl;

import com.cpy.gatherSearch.model.dto.User.UserQueryRequest;
import com.cpy.gatherSearch.model.dto.picture.PictureQueryRequest;
import com.cpy.gatherSearch.model.dto.post.PostQueryRequest;
import com.cpy.gatherSearch.model.dto.search.SearchParms;
import com.cpy.gatherSearch.model.entity.Picture;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.model.entity.Search;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.model.vo.post.PostVO;
import com.cpy.gatherSearch.service.service.PictureService;
import com.cpy.gatherSearch.service.service.PostService;
import com.cpy.gatherSearch.service.service.SearchService;
import com.cpy.gatherSearch.service.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author:成希德
 * search服务实现类
 */
@Service
public class SearchServiceImp implements SearchService {
    @Resource
    UserService userService;
    @Resource
    PostService postService;
    @Resource
    PictureService pictureService;

    /**
     * 聚合搜索获取所有类型数据
     * @param searchParms
     * @return
     * @throws IOException
     */
    @Override
    public Search getAll(SearchParms searchParms) throws IOException {
        String searchValue = searchParms.getSearchValue();
        //todo 根据type返回数据
//     String type = searchParms.getType();
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchValue);
        List<User> userList = userService.getUserList(userQueryRequest);
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setContext(searchValue);
        List<PostVO> postList = postService.getPostListByEs(postQueryRequest);
        PictureQueryRequest pictureQueryRequest = new PictureQueryRequest();
        pictureQueryRequest.setSearchValue(searchValue);
        List<Picture> pictureList = pictureService.getPictureList(searchValue);
        Search search = new Search();
        search.setPictureList(pictureList);
        search.setUserList(userList);
        search.setPostList(postList);
        return search;

    }
}
