package com.cpy.gatherSearch.utils;

import com.cpy.gatherSearch.Exception.CommonException;
import com.cpy.gatherSearch.common.StatuesCode;
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

        User user = getLoginUser(request);
        if(user!=null&&user.getUserRole().equals(UserConstant.ADMIN_ROLE)){
            return true;
        }
        return false;
    }

    /**
     * 判断是否登录
     * @param request
     * @return
     */
    public static User getLoginUser(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user=(User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        return user;
    }

    /**
     * 只有用户自己和管理员才能更改工具类
     * @param request
     * @param userId 用户Id
     * @return
     */
    public static boolean isLoginUserOrAdmin(HttpServletRequest request,Long userId){
        if (IsUser.getLoginUser(request).getId()!=userId
                &&!IsUser.isAdmin(request)){
            return false;
        }
        return true;
    }
}
