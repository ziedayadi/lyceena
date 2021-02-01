package com.zka.lyceena.configuration;


import com.zka.lyceena.services.UserDetailsProvider;
import com.zka.lyceena.services.UserDetailsProviderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories("com.zka.lyceena.dao")
@EntityScan({"com.zka.lyceena.entities.*"})
@Configuration
@ComponentScan({
        "com.zka.lyceena.controllers",
        "com.zka.lyceena.services",
        "com.zka.lyceena.security",
})
public class ApplicationConfiguration {


    @Value( "${jwt.signingkey}" )
    private String signingKey;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDetailsProvider userDetailsProvider(){
        return new UserDetailsProviderImpl(this.signingKey);
    }
}
