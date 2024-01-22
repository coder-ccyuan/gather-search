package com.cpy.gatherSearch.service.service;

import com.cpy.gatherSearch.model.entity.Picture;
import java.io.IOException;
import java.util.List;

public interface PictureService {
    List<Picture> getPictureList(String searchValue) throws IOException;
}
