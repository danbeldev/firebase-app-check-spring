package com.github.danbel.spring.firebase.appcheck.config;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class FirebaseCheckAppErrorHandlerStubConfig {

    @Bean
    public FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler() {
        return (e, request, response, handler) -> {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        };
    }
}
