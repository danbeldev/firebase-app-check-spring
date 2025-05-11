package com.github.danbel.spring.firebase.appcheck.configs;

import com.github.danbel.spring.firebase.appcheck.common.FirebaseAppCheckTokenVerifierServiceMock;
import com.github.danbel.spring.firebase.appcheck.common.TestConstants;
import com.github.danbel.spring.firebase.appcheck.properties.FirebaseAppCheckProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;

@Configuration
public class FirebaseCheckAppConf {

    @Bean
    @Primary
    public FirebaseAppCheckProperties firebaseAppCheckProperties(FirebaseAppCheckProperties properties) {

        return properties;
    }
}
