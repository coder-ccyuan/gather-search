package com.cpy.gatherSearch;

import com.cpy.gatherSearch.dao.mapper.PostMapper;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.service.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author:成希德
 */
@SpringBootTest
public class PostMapperTest {
    @Resource
    PostMapper postMapper;
    @Resource
    PostService postService;
    @Test
    public void addReturnIdTest(){
        Post post = new Post();
        post.setContent("下熬浆糊");
        post.setTitle("tetle");
        post.setUserId((long)12);
        System.out.println(post);
//        int i = postMapper.addReturnId(post);
//        int insert = postMapper.insert(post);
        boolean save = postService.save(post);
        System.out.println(post);
//        System.out.println(i);
    }
}
