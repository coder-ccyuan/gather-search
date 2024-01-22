package com.cpy.gatherSearch.controller;

import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.model.entity.Picture;
import com.cpy.gatherSearch.service.service.PictureService;
import com.cpy.gatherSearch.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author:成希德
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
   @Resource
   PictureService pictureService;
   @GetMapping("/list/query")
   public BaseResponse<List<Picture>> query(String searchValue) throws IOException {
      List<Picture> pictureList = pictureService.getPictureList(searchValue);
      return ResultUtils.success(pictureList);
   }
}
