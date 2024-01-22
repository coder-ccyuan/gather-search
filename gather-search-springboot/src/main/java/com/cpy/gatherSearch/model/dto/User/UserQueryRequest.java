package com.cpy.gatherSearch.model.dto.User;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端请求数据封装类
 * 用户删除接口
 */
@Data
public class UserQueryRequest implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userAccount;

    /**
     * 昵称
     */
    private String userName;

}
