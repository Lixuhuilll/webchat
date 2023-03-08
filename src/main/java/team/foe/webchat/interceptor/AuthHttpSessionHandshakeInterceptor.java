package team.foe.webchat.interceptor;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
public class AuthHttpSessionHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        super.beforeHandshake(request, response, wsHandler, attributes);
        // 如果用户未登录，阻止握手
        if (attributes.get("userName") == null) {
            if (response instanceof ServletServerHttpResponse serverResponse) {
                serverResponse.getServletResponse().sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return false;
        }
        return true;
    }
}
