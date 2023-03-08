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
            // 检查用户是否持有保存了用户名的cookie
            boolean hasUserName = false;
            for (Cookie cookie : request.getCookies()) {
                if ("userName".equals(cookie.getName())) {
                    hasUserName = userName.equals(cookie.getValue());
                }
            }
            // 不带当前用户名的Cookie的话，发放一个Cookie
            if (!hasUserName) {
                Cookie cookie = new Cookie("userName", (String) userName);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return true;
        }
        // 返回 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
