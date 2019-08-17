package cn.akira.interceptor;

import cn.akira.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 会话拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = Logger.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userSession = request.getSession().getAttribute("SESSION_USER");
        if (userSession == null) {
            LOGGER.warn("该操作需要登录有相应权限的用户才能进行,现在跳转至登录页面");
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        if (userSession instanceof User) {
            //这里去数据库查询并核实用户信息

            User user = (User) userSession;
            user.setPassword(null);
            request.getSession().setAttribute("SESSION_USER", user);
            LOGGER.info("用户\"" +user.getUname()+ "\"已登录");
            return true;
        } else {
            LOGGER.error("请先登录");
            return false;
        }
    }
}