package com.example.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

    // 스프링은 ViewResolver를 설정하라. JSP를 사용하려면 InternalResourceViewResolver가 필요하다.
    // InternalResourceViewResolver를 Bean으로 등록하라.
    // /WEB-INF/view/ + "hello" + ".jsp" <- 포워딩
    // jsp는 url을 통하여 직접 접근할 수 없다.
    // Controller를 통해서만 접근할 수 있도록 한다.
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return  resolver;
    }
}
