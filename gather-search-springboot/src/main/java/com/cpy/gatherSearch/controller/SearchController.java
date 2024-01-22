package com.cpy.gatherSearch.controller;

import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.model.dto.search.SearchParms;
import com.cpy.gatherSearch.model.entity.Search;
import com.cpy.gatherSearch.service.service.SearchService;
import com.cpy.gatherSearch.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author:成希德
 * search接口，聚合搜素
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    SearchService searchService;
    @GetMapping("/all")
    public BaseResponse<Search> searchAll(SearchParms searchParms) throws IOException {
        return ResultUtils.success(searchService.getAll(searchParms));
    }
}
