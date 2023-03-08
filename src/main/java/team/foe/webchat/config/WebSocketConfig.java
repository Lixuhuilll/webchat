package team.foe.webchat.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket 为浏览器和服务器提供了双工异步通信的功能，即浏览器可以向服务器发送信息，反之也成立。
 * 但直接使用 WebSocket十分繁琐，所以使用它的子协议 STOMP
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Resource
    HandshakeInterceptor[] handshakeInterceptors;

    /**
     * 客户端可通过该方法中配置的Url与服务器建立STOMP连接
     *
     * @param stompConfig STOMP配置
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompConfig) {
        // 允许原生WebSocket
        stompConfig.addEndpoint("/chat")
                .addInterceptors(handshakeInterceptors);
        /*
          允许使用SockJs方式访问 可通过http://IP:PORT/chat来和服务端建立websocket连接
          SockJS会自动在XHR流、XDR流、iFrame事件源、iFrame HTML文件、XHR轮询等传统http方式中择优选择
         */
        stompConfig.addEndpoint("/chat")
                .addInterceptors(handshakeInterceptors)
                .withSockJS();
    }

    /**
     * 配置已建立STOMP连接的客户端如何与服务器交互
     *
     * @param msgConfig Message配置
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry msgConfig) {
        long[] HEART_BEAT = new long[]{15000L, 15000L};
        // 配置客户端接收服务端消息的地址的前缀信息 以及 配置Ping-Pong
        msgConfig.enableSimpleBroker("/topic")
                .setTaskScheduler(new DefaultManagedTaskScheduler())
                .setHeartbeatValue(HEART_BEAT);
        // 客户端给服务端发消息的地址的前缀
        msgConfig.setApplicationDestinationPrefixes("/app");
    }
}