package com.saper.sistemadeconsultas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost:5173", "http://localhost:5173")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("Authorization", "Content-Type", "Accept")
                .maxAge(3600);
    }
}