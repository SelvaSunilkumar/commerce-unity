package com.github.SelvaSunilkumar.Commerce.Unity.config;

import com.github.SelvaSunilkumar.Commerce.Unity.correlation.CorrelationIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new CorrelationIdInterceptor()));
        return restTemplate;

    }
}
