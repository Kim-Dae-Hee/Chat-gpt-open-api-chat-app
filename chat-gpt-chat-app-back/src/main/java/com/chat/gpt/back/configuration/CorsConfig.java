package com.chat.gpt.back.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:3000") // 허용할 오리진(도메인)을 설정
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 설정
            .allowedHeaders("*"); // 허용할 헤더 설정
    }
}






