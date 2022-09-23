package com.slockey.fantasticoctopotato.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public UserDetailsManager getUserDetailsManager() {
        UserDetails userDetails = User.withUsername("user").password("pass").roles("USER").build();
        return new InMemoryUserDetailsManager(userDetails);
    }

}
