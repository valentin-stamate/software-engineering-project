package com.bfourclass.euopendata;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// FROM -> https://stackoverflow.com/questions/55904200/annotation-crossorigin-not-working-in-spring-boot

//@Configuration
//@EnableWebMvc
//public class CorsConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:63343", "http://localhost:3000", "https://smart-booking-ba548.web.app")
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedHeaders("*")
//                .exposedHeaders("*")
//                .allowCredentials(false).maxAge(3600);
//    }
//}
