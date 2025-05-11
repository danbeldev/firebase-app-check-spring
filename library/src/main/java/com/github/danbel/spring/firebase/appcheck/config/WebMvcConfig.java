package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.core.interceptors.FirebaseAppCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final FirebaseAppCheckInterceptor firebaseAppCheckInterceptor;

    public WebMvcConfig(FirebaseAppCheckInterceptor firebaseAppCheckInterceptor) {
        this.firebaseAppCheckInterceptor = firebaseAppCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firebaseAppCheckInterceptor);
    }
}