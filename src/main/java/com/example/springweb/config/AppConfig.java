package com.example.springweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

// 비즈니스를 처리하는 Service, Repository, Domain을 Bean으로 등록하기 위한 설정.
//@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example"},
        includeFilters = {@ComponentScan.Filter(Service.class),
                @ComponentScan.Filter(Repository.class)})
@EnableJpaRepositories(basePackages = {"com.example.springweb.repository"})
@PropertySource({"classpath:persistence-mysql.properties"})
public class AppConfig {
    // 의존성 주입과 관련된 어노테이션
    @Autowired // Autowired만 넣어주면 = 스프링이 객체를 초기화한다;
    private Environment env; // ket, value <- persistence-mysql.properties 정보가 들어가 있다.

    // DataSource는 커넥션 풀이라고도 한다.
    // 데이터베이스 접속과 관련된 객체(DataSource)를 만들어준다.
    // DataSource는 인터페이스이다.
    // persistence-mysql.properties에 있는 정보를 이용해 객체를 만든다.
    @Bean
    public DataSource dataSource(){

        System.out.println("jdbc.driverClassName");
        System.out.println(env.getProperty("jdbc.driverClassName"));
        final DriverManagerDataSource dataSource = new DriverManagerDataSource(); // DataSource 구현체
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }

    // JPA를 다루려면 트랜잭션 처리를 한다.
    // 트랜잭션 시작, SQL실행, 트랜잭션 종료 (commit or rollback)
    // 스프링을 만든 사람은 SQL 실행, 트랜잭션과 시작, 종료는 자동으로 하게할 수 없을까?
    // @Transactional 에노테이션을 이용해서 트랜잭션을 자동으로 처리할 수 있게 해준다.
    // 트랜잭션을 관리하는 Bean이 필요하다.
    // 공장을 만드는게 쉬울까? 공장에서 상품을 만드는게 쉬울까? 당연히 공장 건설이 어렵다.
    // EntityManagerFactory도 Bean으로 등록되야 한다.
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf){
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    // LocalContainerEntityManagerFactoryBean은 EntityManagerFactory을 만들어주는 Bean이다.
    // LocalContainerEntityManagerFactoryBean DataSource가 필요하고, HibernateJPAVendorAdapter가 필요하다.
    // JPA는 ORM표준. 인터페이스. 구현체는 Hibernate, EclipseLink, OpenJPA 등이 있다.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.example"});

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean // JPA와 관련된 Exception을 스프링과 관련된 Exception으로 자동변환된다.
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        return hibernateProperties;
    }
}
