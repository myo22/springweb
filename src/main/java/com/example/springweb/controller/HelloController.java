package com.example.springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.io.IOException;

@Controller
public class HelloController {

//    @Autowired
//    DataSource dataSource; 스프링이 Bean으로 관리하는 객체는 Autowired만 넣어주면 자동으로 넣을 수 있다.

    @GetMapping("/hello") // /hello 요청이 오면 해당 메소드를 실행하라.
    // 이해를 돕기위해 서블릿과 유사하게 만든거지 스프링에서 이렇게 사용하지 않는다.
    public String hello() throws IOException {
        // 로직을 수행.
        return "hello"; // hello.jsp가 되기를 원한다. -> hello.jsp로 포워딩.
    }
}

// URL을 처리하는 컨트롤러의 메소드가 hello를 리턴하면 hello.jsp로 포워딩하고 싶다.

//javax.servlet.ServletException: Circular view path [hello]: would dispatch back to the current handler URL [/hello] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
//	org.springframework.web.servlet.view.InternalResourceView.prepareForRendering(InternalResourceView.java:210)
//	org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:148)
//	org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:316)
//	org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1405)
//	org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1149)
//	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1088)
//	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:964)
//	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
//	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:489)
//	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
//	javax.servlet.http.HttpServlet.service(HttpServlet.java:583)
//	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)