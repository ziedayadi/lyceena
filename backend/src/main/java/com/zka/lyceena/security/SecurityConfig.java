package com.zka.lyceena.security;

import com.zka.lyceena.configuration.KeycloakRealmRoleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value( "${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}" )
    private String jwkSetUri;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/students/**").hasAnyRole("ADMIN")
                .antMatchers("/class-levels-ref/**").hasAnyRole("ADMIN")
                .antMatchers("/employees/**").hasAnyRole("ADMIN")
                .antMatchers("/classes/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/teachers/**").hasAnyRole("ADMIN","TEACHER")
                .antMatchers(HttpMethod.POST,"/teachers/**").hasAnyRole("ADMIN")
                .antMatchers("/ref/materials/**").hasAnyRole("ADMIN")
                .antMatchers("/parents/**").hasAnyRole("ADMIN")
                .antMatchers("/class-rooms/**").hasAnyRole("ADMIN")
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}
