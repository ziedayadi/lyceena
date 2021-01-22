package com.zka.lyceena.test.config;


import com.zka.lyceena.configuration.DataInit;
import com.zka.lyceena.services.UserDetailsProvider;
import com.zka.lyceena.test.services.UserDetailsProviderTestImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "com.zka.lyceena.dao")
@EntityScan({"com.zka.lyceena.entities.*"})
@Configuration
@ComponentScan(basePackages = {
        "com.zka.lyceena.controllers",
        "com.zka.lyceena.services",
        "com.zka.lyceena.security",
})
@EnableTransactionManagement
public class TestApplicationContextConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.zka.lyceena.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(addotionalProperties());
        return em;
    }

    private Properties addotionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDetailsProvider userDetailsProvider() {
        return new UserDetailsProviderTestImpl();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        jpaTransactionManager.setJpaProperties(properties());
        return jpaTransactionManager;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    public DataInit dataInit() {
        return new DataInit();
    }

    @Bean
    public void initializeDataForTests() {
        dataInit().initClassYears();
        dataInit().initDays();
        dataInit().initHours();
        dataInit().initMaterialRef();
        dataInit().initClassRef();
        dataInit().initNumberOfHours();
        dataInit().initClassRooms();
        dataInit().initClasses();
        dataInit().initParents();
        dataInit().initStudents();
        dataInit().initEmployeeRefType();
        dataInit().initEmployee();
        dataInit().initSessions();
    }
}
