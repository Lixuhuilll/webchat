package team.foe.webchat.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public Message login(String username, String password
            , HttpServletRequest request, HttpServletResponse response) {
        User user = userService.login(username, password);
        Message message = new Message();
        if (user == null) {
            message.setErrorCode(403);
            message.setMsg("登录失败，用户名或密码错误");
        } else {
            // session 中写入键值对
            request.getSession().setAttribute("userName", user.getName());
            // 发放Cookie，不设置过期时间表示关闭浏览器即过期
            Cookie cookie = new Cookie("userName", user.getName());
            cookie.setPath("/");
            response.addCookie(cookie);
            // 告知前端登录成功
            message.setErrorCode(200);
            message.setMsg("OK");
        }
        return message;
    }

    @PostMapping("/signup")
    public Message signup(String username, String password) {
        User user = userService.signup(username, password);
        Message message = new Message();
        if (user == null) {
            message.setErrorCode(403);
            message.setMsg("注册失败");
        } else {
            message.setErrorCode(200);
            message.setMsg("OK");
        }
        return message;
    }
}
