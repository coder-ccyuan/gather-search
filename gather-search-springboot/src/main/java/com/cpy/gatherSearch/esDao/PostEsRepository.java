package com.cpy.gatherSearch.esDao;

import com.cpy.gatherSearch.model.dto.post.PostEsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostEsRepository extends ElasticsearchRepository<PostEsDTO,Long> {

}
