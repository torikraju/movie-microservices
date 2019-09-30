package com.movie.informationService.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestBean {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
