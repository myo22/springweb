package com.example.springweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// 비즈니스를 처리하는 Service, Repository, Domain을 Bean으로 등록하기 위한 설정.
@Configuration
@PropertySource({"classpath:persistence-mysql.properties"})
public class AppConfig {
    // 의존성 주입과 관련된 어노테이션
    @Autowired // Autowired만 넣어주면 = 스프링이 객체를 초기화한다;
    private Environment env; // ket, value <- persistence-mysql.properties 정보가 들어가 있다.

    // 데이터베이스 접속과 관련된 객체(DataSource)를 만들어준다.
    // DataSource는 인터페이스이다.
    // persistence-mysql.properties에 있는 정보를 이용해 객체를 만든다.
    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource(); // DataSource 구현체
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }
}
