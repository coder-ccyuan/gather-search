package com.cpy.gatherSearch.service.service.impl;

import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.dao.mapper.PostMapper;
import com.cpy.gatherSearch.esDao.PostEsRepository;
import com.cpy.gatherSearch.model.dto.post.PostAddRequest;
import com.cpy.gatherSearch.model.dto.post.PostEsDTO;
import com.cpy.gatherSearch.model.dto.post.PostQueryRequest;
import com.cpy.gatherSearch.model.dto.post.PostUpdateRequest;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.PostService;
import com.cpy.gatherSearch.service.service.UserService;
import com.cpy.gatherSearch.utils.IsUser;
import com.cpy.gatherSearch.utils.VerifyUtils;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


/**
 * @author 成希德
 * @description 针对表【post(帖子)】的数据库操作Service实现
 * @createDate 2024-01-23 20:29:11
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {
    @Resource
    UserService userService;
    @Resource
    PostEsRepository repository;
    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 添加帖子服务
     *
     * @param addRequest
     * @return
     */
    @Override
    @Transactional
    public boolean addPost(PostAddRequest addRequest, HttpServletRequest request) {
        String title = addRequest.getTitle();
        String content = addRequest.getContent();
        String tags = addRequest.getTags();
        Integer thumbNum = addRequest.getThumbNum();
        Integer favourNum = addRequest.getFavourNum();
        if (!VerifyUtils.verifyString(title) || !VerifyUtils.verifyString(content)) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "标题，内容不能为空");
        }
        //todo 验证tags
        //验证用户是否存在
        User loginUser = IsUser.getLoginUser(request);
        if (loginUser == null) {
            throw new CommonException(StatuesCode.NO_LOGIN, "未登录");
        }
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setTags(tags);
        post.setThumbNum(thumbNum);
        post.setFavourNum(favourNum);
        post.setUserId(loginUser.getId());
        boolean flag = this.save(post);
        if (!flag) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "帖子参数错误");
        }
        /*es存储*/
        //获取mysql中的post
        Post byId = getById(post.getId());
        boolean b = saveAndUpdatePostEs(byId);
        if (!b) {
            throw new CommonException(StatuesCode.SYSTEM_EXCEPTION, "elastic serch error");
        }

        return true;
    }

    /**
     * 帖子更新服务
     *
     * @param updateRequest
     * @return
     */
    @Transactional//保证es和mysql同步
    @Override
    public boolean updatePost(PostUpdateRequest updateRequest) {
        Long id = updateRequest.getId();
        String title = updateRequest.getTitle();
        String tags = updateRequest.getTags();
        String content = updateRequest.getContent();
        if (!VerifyUtils.verifyString(title) || !VerifyUtils.verifyString(content)) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "标题和内容不能为空");
        }
        //todo 验证tags
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setTags(tags);
        boolean b = this.updateById(post);
         post = this.getById(post.getId());
        //同步更新es
        //mysql存储失败
        if (!b) {
            throw new CommonException(StatuesCode.PARAMS_ERROR, "帖子参数错误");
        }
        //mysql成功
        b = saveAndUpdatePostEs(post);
        //es存储失败
        if (!b) {
            throw new CommonException(StatuesCode.SYSTEM_EXCEPTION, "elastic search error");
        }
        return true;
    }

    @Override
    public List<Post> getPostList(PostQueryRequest queryRequest) {
        String context = queryRequest.getContext();
        QueryWrapper<Post> qw = new QueryWrapper<>();
        if (!VerifyUtils.verifyString(context)) {
            List<Post> list = this.list();
            return list;
        }
        qw.like("title", context).or().like("content", context);
        List<Post> list = this.list(qw);
        return list;
    }

    /**
     * 更新和添加文档到post index中
     *
     * @param post
     * @return
     */
    @Override
    public boolean saveAndUpdatePostEs(Post post) {
        PostEsDTO postEsDTO = BeanUtil.toBean(post, PostEsDTO.class);
        PostEsDTO save = repository.save(postEsDTO);
        if (save == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePostEs(Long id) {
        return false;
    }


    /**
     * 从es获取doc
     *
     * @param queryRequest
     * @return
     */
    @Override
    public List<Post> getPostListByEs(PostQueryRequest queryRequest) {
        String context = queryRequest.getContext();
        LinkedList<Post> postList = new LinkedList<>();
        if (!VerifyUtils.verifyString(context)) {
            Iterable<PostEsDTO> all = repository.findAll();
            for (PostEsDTO postEsDTO : all) {
                postList.add(BeanUtil.toBean(postEsDTO, Post.class));
            }
            return postList;
        }
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(context, "content", "title");
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).build();
        SearchHits<PostEsDTO> search = elasticsearchRestTemplate.search(build, PostEsDTO.class);
        for (SearchHit<PostEsDTO> postEsDTOSearchHit : search) {
            PostEsDTO content = postEsDTOSearchHit.getContent();
            Post post = BeanUtil.toBean(content, Post.class);
            postList.add(post);
        }
        return postList;
    }

    /**
     * 删除数据，es mysql 同步删除
     * @param id
     * @param request
     * @return
     */
    @Override
    @Transactional //保证同步删除
    public boolean deletePost(Long id,HttpServletRequest request) {
        Post post = getById(id);
        if (post==null){
            throw new CommonException(StatuesCode.PARAMS_ERROR,"帖子不存在");
        }
        //普通用户删除或管理员
        if(post.getUserId()!=IsUser.getLoginUser(request).getId()
        && !IsUser.isAdmin(request)){
            throw new CommonException(StatuesCode.NO_AUTH,"没有删除帖子的权限");
        }
        boolean flag = removeById(id);
        if (!flag){
            throw new CommonException(StatuesCode.PARAMS_ERROR,"帖子id不存在");
        }
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new CommonException(StatuesCode.SYSTEM_EXCEPTION,"es错误");
        }
        return true;
    }

    @Override
    public List<Post> getListByUserId(Long id) {
        TermQueryBuilder termQb = QueryBuilders.termQuery("userId", id);
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(termQb).build();
        SearchHits<PostEsDTO> search = elasticsearchRestTemplate.search(build, PostEsDTO.class);
        LinkedList<Post> posts = new LinkedList<>();
        for (SearchHit<PostEsDTO> postEsDTOSearchHit : search) {
            PostEsDTO content = postEsDTOSearchHit.getContent();
            posts.add(BeanUtil.toBean(content,Post.class));
        }
        return posts;
    }
}




