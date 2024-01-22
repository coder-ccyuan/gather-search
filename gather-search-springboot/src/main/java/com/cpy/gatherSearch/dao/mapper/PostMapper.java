package com.cpy.gatherSearch.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpy.gatherSearch.model.entity.Post;


/**
* @author 成希德
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2024-01-23 20:29:11
* @Entity generator.domain.Post
*/
public interface PostMapper extends BaseMapper<Post> {
 int addReturnId(Post post);
}




