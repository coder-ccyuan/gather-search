package com.cpy.gatherSearch.model.dto.User;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端请求数据封装类
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     *  性别，0为女，1为男
     */
    private Integer gender;

    /**
     *  权限，0为普通用户，1为管理员用户
     */
    private Integer role;




}
