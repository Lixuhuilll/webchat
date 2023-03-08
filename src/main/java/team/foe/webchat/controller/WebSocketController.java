package team.foe.webchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import team.foe.webchat.entity.Message;

/**
 * 这个Controller处理的是发送消息的URL和订阅消息的URL
 * 在实现WebSocket-STOMP聊天室时，将建立连接的URL、发送消息的URL和订阅消息的URL合并为一个URL也是可以实现功能的。
 * 但是WebSocket协议的设计原则之一就是将不同类型的请求分开处理，以提高协议的可读性、可维护性和安全性。
 * 将建立连接、发送消息和订阅消息的请求合并为一个URL，会让协议变得难以理解和维护，同时也会增加安全风险。
 * 此外，如果将这三个URL合并为一个URL，就需要在WebSocket客户端的代码中进行额外的处理，以区分不同的请求类型，
 * 这样会使代码变得更加复杂和难以维护。
 * 因此，为了符合WebSocket协议的设计原则，建议将建立连接的URL、发送消息的URL和订阅消息的URL分开处理，
 * 以提高协议的可读性、可维护性和安全性。
 */
@Controller
public class WebSocketController {
    /**
     * 可以接收客户端通过“/app/chat”发送过来的消息 <br>
     * 客户端需要在"/topic/chat"上监听并接收服务端发回的消息
     *
     * @param message 消息体
     * @return message 消息体 将收到的消息广播给所有正在监听的用户
     */
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Message send(SimpMessageHeaderAccessor headerAccessor, Message message) {
        String name = (String) headerAccessor.getSessionAttributes().get("userName");
        message.setFrom(name);
        message.setNowTime();
        return message;
    }
}
