package com.progressSoft.demo.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanProviderConfig {
    @Bean
    public RestTemplate getRestTemplate() {return new RestTemplate();}
}
