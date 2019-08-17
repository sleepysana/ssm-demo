package cn.akira.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法耗时统计拦截器
 */
public class MethodTimerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(MethodTimerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //记录开始时间
        long startTime = System.currentTimeMillis();
        //将开始时间存到请求域
        request.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //从请求域获取开始时间
        long startTime = (long) request.getAttribute("startTime");
        //记录结束时间
        long endTime = System.currentTimeMillis();
        //计算耗时
        long detaTime = endTime - startTime;
        String message = "[" + request.getRequestURI() + "] 请求耗时" + detaTime + "ms";
        if(detaTime>1000){
            LOGGER.warn(message);
        }else{
            LOGGER.info(message);
        }
    }
}
