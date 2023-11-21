package com.cpy.gatherSearch.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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

    /**
     *  0为离线，1为上线
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  0表示未删除，1表示删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}