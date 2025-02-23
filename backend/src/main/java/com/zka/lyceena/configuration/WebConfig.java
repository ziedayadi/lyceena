package com.zka.lyceena.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value( "${cors.allowed.origins}" )
    private String allowedOrigin;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name());
    }
}
