package com.cpy.gatherSearch.service.service;

import com.cpy.gatherSearch.model.dto.search.SearchParms;
import com.cpy.gatherSearch.model.entity.Search;

import java.io.IOException;

public interface SearchService {
    Search getAll(SearchParms searchParms) throws IOException;
}
