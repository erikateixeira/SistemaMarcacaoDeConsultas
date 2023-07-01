package com.saper.sistemadeconsultas.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
                //.apiInfo(apiInfo());
    }
/*
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API de Consultas Médicas")
                .description("API para gerenciamento de consultas médicas")
                .version("1.0.0")
                //.contact(new Contact("Seu Nome", "", "seuemail@example.com"))
                .build();
    }


}*/