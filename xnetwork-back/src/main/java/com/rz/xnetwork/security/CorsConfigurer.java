package com.rz.xnetwork.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rz.xnetwork.config.Config;

@Configuration
public class CorsConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(Config.SERVER_URL)
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
    }
}