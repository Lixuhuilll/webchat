package team.foe.webchat.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.foe.webchat.entity.Message;
import team.foe.webchat.entity.User;
import team.foe.webchat.service.UserService;

@RestController
public class UserController {
    @Resource
    UserService userService;
    @PostMapping("/login")
    public Message login(String username, String password, HttpServletRequest request) {
        User user = userService.login(username, password);
        Message message = new Message();
        if(user == null) {
            message.setErrorCode(403);
            message.setMsg("登录失败，用户名或密码错误");
        }
        else {
            // session 中写入键值对
            request.getSession().setAttribute("userName", user.getName());
            message.setErrorCode(200);
            message.setMsg("OK");
        }
        return message;
    }
    @PostMapping("/signup")
    public Message signup(String username, String password) {
        User user = userService.signup(username, password);
        Message message = new Message();
        if(user == null) {
            message.setErrorCode(403);
            message.setMsg("注册失败");
        }
        else {
            message.setErrorCode(200);
            message.setMsg("OK");
        }
        return message;
    }
}
