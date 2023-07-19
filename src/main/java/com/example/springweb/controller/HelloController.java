package com.example.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {
    @GetMapping("/hello") // /hello 요청이 오면 해당 메소드를 실행하라.
    // 이해를 돕기위해 서블릿과 유사하게 만든거지 스프링에서 이렇게 사용하지 않는다.
    public void hello(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Hello Spring MVC");
        out.close();
    }
}
