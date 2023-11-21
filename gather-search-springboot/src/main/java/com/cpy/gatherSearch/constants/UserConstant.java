package com.cpy.gatherSearch.constants;

public interface UserConstant {
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
    int ADMIN_ROLE=1;
    /**
     * 普通用户权限
     */
    int DEFAULT_ROLE=0;
    /**
     * 女
     */
    int GENDER_WOMAN=0;    /**
     * 男
     */
    int GENDER_MAN=1;
}
