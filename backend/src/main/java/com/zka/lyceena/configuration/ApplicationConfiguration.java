package com.zka.lyceena.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan({"com.zka.lyceena.entities.*"})
@Configuration
public class ApplicationConfiguration {
}
