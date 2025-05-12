package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FirebaseCheckAppErrorHandlerMockConfig {

    @Bean
    public FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler() {
        return Mockito.mock(FirebaseAppCheckErrorHandler.class);
    }
}
