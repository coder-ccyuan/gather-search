package com.cpy.gatherSearch;

import cn.hutool.core.bean.BeanUtil;
import com.cpy.gatherSearch.esDao.PostEsRepository;
import com.cpy.gatherSearch.model.dto.post.PostEsDTO;
import com.cpy.gatherSearch.model.entity.Post;
import com.cpy.gatherSearch.service.service.PostService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:成希德
 */
@SpringBootTest
public class SpringDataESTest {
    @Resource
    ElasticsearchRestTemplate restTemplate;
    @Resource
    PostEsRepository postEsRepository;
    @Resource
    PostService postService;

    @Test
    public void testCreate() {

    }

    @Test
    public void addData() {
        List<Post> list = postService.list();
        for (Post post : list) {
            PostEsDTO postEsDTO = new PostEsDTO();
            BeanUtil.copyProperties(post,postEsDTO);
            System.out.println(postEsDTO);
            PostEsDTO save = postEsRepository.save(postEsDTO);
            System.out.println(postEsRepository);
        }
    }
    @Test
    public void getDatas(){
        Iterable<PostEsDTO> all = postEsRepository.findAll();
        for (PostEsDTO postEsDTO : all) {
            System.out.println(postEsDTO);
        }
    }
    @Test

    public void testQueryCustom(){
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("content", "小黑子是你吗");
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("你是黑子", "content", "title");
        // 构造查询

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).build();
        SearchHits<PostEsDTO> search = restTemplate.search(searchQuery,PostEsDTO.class);
        List<SearchHit<PostEsDTO>> searchHits = search.getSearchHits();
        for (SearchHit<PostEsDTO> searchHit : searchHits) {
            PostEsDTO content = searchHit.getContent();
            System.out.println(content);

        }


    }
    @Test
    public void deleteTest(){
    postEsRepository.deleteById(2L);


    }

}
