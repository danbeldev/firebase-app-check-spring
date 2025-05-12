package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.common.FirebaseAppCheckTokenVerifierServiceMock;
import com.github.danbel.spring.firebase.appcheck.core.interceptors.FirebaseAppCheckInterceptor;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;
import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.impl.FirebaseAppCheckErrorHandlerImpl;
import com.github.danbel.spring.firebase.appcheck.exception.properties.FirebaseAppCheckErrorProperties;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
@EnableConfigurationProperties({FirebaseAppCheckProperties.class, FirebaseAppCheckErrorProperties.class})
public class FirebaseAppCheckConfig {

    @Bean
    @Primary
    public FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler(
            FirebaseAppCheckErrorProperties firebaseAppCheckErrorProperties
    ) {
        return new FirebaseAppCheckErrorHandlerImpl(firebaseAppCheckErrorProperties);
    }

    @Bean
    @Primary
    public FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService() {
        return new FirebaseAppCheckTokenVerifierServiceMock();
    }

    @Bean
    @Primary
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
