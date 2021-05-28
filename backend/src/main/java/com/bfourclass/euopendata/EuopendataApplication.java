package com.bfourclass.euopendata;

import com.bfourclass.euopendata.external_api.covid.CovidStatisticsAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EuopendataApplication {

    public static void main(String[] args) throws InterruptedException {
        /* This thread is updating covid statistics once a day */
        CovidStatisticsAPI ins = new CovidStatisticsAPI();
        Thread thread = new Thread(ins);
        thread.start();

        Thread.sleep(4000);

        SpringApplication.run(EuopendataApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
