package com.zka.lyceena.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.zka.lyceena.dao")
@EntityScan({"com.zka.lyceena.entities.*"})
@Configuration
@ComponentScan({
        "com.zka.lyceena.controllers",
        "com.zka.lyceena.services",
        "com.zka.lyceena.security",
})
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
