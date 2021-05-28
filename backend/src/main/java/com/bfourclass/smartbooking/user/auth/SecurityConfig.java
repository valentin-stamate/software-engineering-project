package com.bfourclass.smartbooking.user.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityContext securityContext() {
        return new InMemorySecurityContext();
    }
}
