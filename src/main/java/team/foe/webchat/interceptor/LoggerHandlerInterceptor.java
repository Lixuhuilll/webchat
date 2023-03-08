package team.foe.webchat.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (null == request.getSession().getAttribute("userName")) {
            // 未登录，放行到登录页面
            return true;
        }
        // 已登录的用户不允许重复登录
        request.getRequestDispatcher("/logged").forward(request, response);
        return false;
    }
}
