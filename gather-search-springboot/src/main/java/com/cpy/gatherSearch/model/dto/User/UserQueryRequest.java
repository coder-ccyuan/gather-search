package com.cpy.gatherSearch.model.dto.User;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryRequest implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

}
