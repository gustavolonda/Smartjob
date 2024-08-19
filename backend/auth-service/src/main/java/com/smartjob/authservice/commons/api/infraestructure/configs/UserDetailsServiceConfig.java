package com.smartjob.authservice.commons.api.infraestructure.configs;

import com.smartjob.authservice.user.domains.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsServiceConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(); // Ensure UserInfoService implements UserDetailsService
    }
}
