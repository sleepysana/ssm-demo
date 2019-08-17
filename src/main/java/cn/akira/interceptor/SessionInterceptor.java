package cn.akira.interceptor;

import cn.akira.pojo.User;
import cn.akira.service.UserService;
import cn.akira.util.ServletUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 会话拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = Logger.getLogger(SessionInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userSession = request.getSession().getAttribute("SESSION_USER");
        if (userSession == null) {
            LOGGER.warn("["+request.getRequestURI()+"] 需要登录有相应权限的用户才能进行,现在跳转至登录页面");
            ServletUtil.redirectOutOfIframe(request.getContextPath()+"/login.jsp",request,response);
            return false;
        }
        if (userSession instanceof User) {
            User user = (User) userSession;

            //这里去数据库查询并核实用户信息
//            if (userService.getUser(user)==null){
//                LOGGER.warn("["+request.getRequestURI()+"] 请求需要登录有相应权限的用户才能继续");
//                return false;
//            }
            user.setPassword(null);
            request.getSession().setAttribute("SESSION_USER", user);
            LOGGER.info("会话用户名:\"" +user.getUname()+ "\"");
            return true;
        } else {
            LOGGER.error("请先登录");
            ServletUtil.redirectOutOfIframe("/login.jsp",request,response);
            return false;
        }
    }
}