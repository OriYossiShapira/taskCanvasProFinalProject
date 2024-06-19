package com.ori.taskcanvaspro.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    /*


    @Bean
    public WebMvcConfigurer getWebConfigurer() {
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods(new String[]{"GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE"});
            }
        };
    }

     */
}
