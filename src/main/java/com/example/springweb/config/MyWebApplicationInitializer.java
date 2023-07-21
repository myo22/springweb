package com.example.springweb.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer; // 3.1에서 등장 -> 소스코드를 너무 많이 작성한다
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

// WebApplicationInitializer 구현하는 아래의 클래스를 제공
// AbstractAnnotationConfigDispatcherServletInitializer <- 3.2
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 한글이 깨지지 않도록 설정 - 필터설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {encodingFilter};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    // 스프링 컨테이너 ---- 웹과 관련된 설정(Spring MVC - getServletMappings) , 비즈니스로직과 관련된 설정 (트랜잭션, DB프로그래밍 - getRootConfigClasses)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfig.class }; //
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/"}; // 모든 요청을 DispatcherServlet으로 보낸다.
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        System.out.println("onStartup이 실행됩니다.");
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebConfig.class, AppConfig.class);
//
//        DispatcherServlet servlet = new DispatcherServlet(context); // ApplicationContext를 생성자로하여 가지고 있다.
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/");
}
}
