package com.cpy.gatherSearch.utils;


import com.cpy.gatherSearch.constants.UserConstant;
import com.cpy.gatherSearch.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 判断用户权限，登录相关工具类
 */
public class IsUser {
    /**
     *
     * @param request
     * 判断是否为管理员
     * @return true 判断为是管理员 false 判断为不是管理员
     */
    public static boolean isAdmin(HttpServletRequest request){

        HttpSession session = request.getSession();
         User user=(User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user==null){
            return false;
        }
        if(user.getRole()==UserConstant.ADMIN_ROLE){
            return true;
        }
        return false;
    }


}
