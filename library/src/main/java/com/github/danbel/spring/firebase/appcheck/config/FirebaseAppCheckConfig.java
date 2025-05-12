package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.core.interceptors.FirebaseAppCheckInterceptor;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;
import com.github.danbel.spring.firebase.appcheck.core.services.impl.FirebaseAppCheckTokenVerifierServiceImpl;
import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.impl.FirebaseAppCheckErrorHandlerImpl;
import com.github.danbel.spring.firebase.appcheck.exception.properties.FirebaseAppCheckErrorProperties;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FirebaseAppCheckProperties.class, FirebaseAppCheckErrorProperties.class})
public class FirebaseAppCheckConfig {

    @Bean
    @ConditionalOnMissingBean(FirebaseAppCheckErrorHandler.class)
    public FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler(
            FirebaseAppCheckErrorProperties firebaseAppCheckErrorProperties
    ) {
        return new FirebaseAppCheckErrorHandlerImpl(firebaseAppCheckErrorProperties);
    }

    @Bean
    @ConditionalOnMissingBean(FirebaseAppCheckTokenVerifierService.class)
    public FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService() {
        return new FirebaseAppCheckTokenVerifierServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(FirebaseAppCheckInterceptor.class)
    public FirebaseAppCheckInterceptor firebaseAppCheckInterceptor(
            FirebaseAppCheckProperties properties,
            FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler,
            FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService
    ) {
        return new FirebaseAppCheckInterceptor(
                properties,
                firebaseAppCheckErrorHandler,
                firebaseAppCheckTokenVerifierService
        );
    }
}