package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.core.interceptors.FirebaseAppCheckInterceptor;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FirebaseAppCheckProperties.class)
@ConditionalOnClass(FirebaseAppCheckInterceptor.class)
public class FirebaseAppCheckConfig {

    @Bean
    public FirebaseAppCheckInterceptor firebaseAppCheckInterceptor(FirebaseAppCheckProperties properties) {
        return new FirebaseAppCheckInterceptor(properties);
    }
}