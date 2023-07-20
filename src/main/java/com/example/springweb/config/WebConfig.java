package com.example.springweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.awt.*;

// 스프링 설정.
@Configuration // Spring Java Config를 알려주는것.
@EnableWebMvc // <mvc:annotation-driven /> <- xml 태그에선 이렇게 쓰여진다.
// com.example패키지 이하에서 @Controller가 붙은 클래스를 다 Bean으로 등록해달라.
@ComponentScan(basePackages = { "com.example"},
        includeFilters = { @ComponentScan.Filter(Controller.class) })
public class WebConfig implements WebMvcConfigurer {
    public WebConfig() {
        System.out.println("WebConfig가 실행됩니다.");
    }

    // Tomcat이 기본으로 제공하는 서블릿(정적자원)을 사용하도록 설정.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("configureDefaultServletHandling이 실행됩니다.");
        configurer.enable(); // 디폴트 서블릿을 사용하도록 설정.
    }
}
