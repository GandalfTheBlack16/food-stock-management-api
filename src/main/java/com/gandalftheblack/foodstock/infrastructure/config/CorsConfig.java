package com.gandalftheblack.foodstock.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Value("${foodstock.api.cors.allowed-origins}")
    private String[] allowedOrigins;
    private final static String[] allowedMethods = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };
    private final static String[] allowedHeaders = { "*" };
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        .allowedMethods(allowedMethods)
                        .allowedHeaders(allowedHeaders)
                        .allowedOrigins(allowedOrigins);
            }
        };
    }
}
