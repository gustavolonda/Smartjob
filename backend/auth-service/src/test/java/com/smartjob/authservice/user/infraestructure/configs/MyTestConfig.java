package com.smartjob.authservice.user.infraestructure.configs;

import com.smartjob.authservice.commons.api.domains.data.UtilApi;
import com.smartjob.authservice.user.applications.rest.AuthenticationController;
import com.smartjob.authservice.user.domains.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MyTestConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public AuthenticationController authenticationController() {
        return new AuthenticationController();
    }
    @Bean
    public MessageSource messageSource() {
        return Mockito.mock(MessageSource.class);
    }
    @Bean
    public UtilApi utilApi() {
        return Mockito.mock(UtilApi.class);
    }
    @Bean
    public MessageSource estaticMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}