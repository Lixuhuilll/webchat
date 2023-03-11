package team.foe.webchat.config;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    HandlerInterceptor authHandlerInterceptor;
    @Resource
    HandlerInterceptor loggerHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/**/chat*", "/logged");
        registry.addInterceptor(loggerHandlerInterceptor)
                .addPathPatterns("/**/login*", "/signup.html");
    }
}
