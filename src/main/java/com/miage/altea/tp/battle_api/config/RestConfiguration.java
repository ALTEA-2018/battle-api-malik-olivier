package com.miage.altea.tp.battle_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {
    @Value("${trainer.service.username}")
    private String username;

    @Value("${trainer.service.password}")
    private String password;

    @Bean
    RestTemplate trainerApiRestTemplate() {
        BasicAuthenticationInterceptor basic = new BasicAuthenticationInterceptor(username, password);
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(basic));
        return template;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
