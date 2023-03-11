package team.foe.webchat.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response
            , Object handler) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        if (userName != null) {
            return true;
        }
        // 返回 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
