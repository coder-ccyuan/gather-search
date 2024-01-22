package com.cpy.gatherSearch.constants;

public interface UserConstant {
    /**
     * session域中用户信息key
     */
    String USER_SESSION_KEY="userSessionKey";
    /**
     * 加密SALT
     */
    String SALT="cpy";
    /**
     * 用户登录状态
     */
    String USER_LOGIN_STATE="userLoginState";
    /**
     * 管理源权限
     */
    String ADMIN_ROLE="admin";
    /**
     * 普通用户权限
     */
    String DEFAULT_ROLE="user";
    /**
     * 封禁用户
     */
    String BAN_ROLE="ban";
    /**
     * 女
     */
    int GENDER_WOMAN=0;
    /**
     * 男
     */
    int GENDER_MAN=1;
}
