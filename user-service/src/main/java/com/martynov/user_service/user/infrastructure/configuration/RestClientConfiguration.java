package com.martynov.user_service.user.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
    @Value("${rest.client.track.url}")
    private String url;

    @Bean
    public RestClient trackRestClient() {
        return RestClient.builder().baseUrl(url).build();
    }
}
