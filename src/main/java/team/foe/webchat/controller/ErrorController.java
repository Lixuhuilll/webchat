package team.foe.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.foe.webchat.entity.Message;

@Controller
public class ErrorController {
    @GetMapping("/logged")
    public String htmlLogged() {
        return "/error/logged.html";
    }
    @RequestMapping ("/logged")
    @ResponseBody
    public Message restLogged() {
        Message message = new Message();
        message.setErrorCode(403);
        message.setMsg("已在线，无须重复登录");
        return message;
    }
}
