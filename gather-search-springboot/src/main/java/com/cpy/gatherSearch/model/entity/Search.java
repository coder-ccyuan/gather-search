package com.cpy.gatherSearch.model.entity;

import javafx.geometry.Pos;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:成希德
 */
@Data
public class Search implements Serializable {
   private List<Picture> pictureList;
   private List<Post> postList;
   private List<User> userList;
}
