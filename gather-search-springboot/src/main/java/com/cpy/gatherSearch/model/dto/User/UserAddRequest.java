package com.cpy.gatherSearch.model.dto.User;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 前端请求数据封装类
 * 用户添加接口
 */
@Data
public class UserAddRequest implements Serializable {


    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userName;







}
