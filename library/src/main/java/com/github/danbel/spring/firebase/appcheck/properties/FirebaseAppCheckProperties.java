package com.github.danbel.spring.firebase.appcheck.properties;

import com.github.danbel.spring.firebase.appcheck.error.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.services.FirebaseAppCheckTokenVerifierService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FirebaseAppCheckProperties {

    @Value("${firebase.app-check.project-id}")
    private String firebaseProjectId = "";

    @Value("${firebase.app-check.project-id}")
    private String firebaseProjectNumber = "";

    @Value("${firebase.app-check.project-id}")
    private String firebaseAppCheckHeaderName = "X-Firebase-AppCheck";

    @Value("${firebase.app-check.project-id}")
    private String firebaseAppCheckApiBaseUrl = "https://firebaseappcheck.googleapis.com";

    @Value("${firebase.app-check.project-id}")
    private String firebaseAppCheckPublicKeyUrl = firebaseAppCheckApiBaseUrl + "/v1/jwks";

    @Value("${it.pierfani.firebaseappcheck.enabled}")
    private boolean firebaseAppCheckEnabled;

    @Value("${firebase.app-check.project-id}")
    private FirebaseAppCheckSecureStrategy secureStrategy = FirebaseAppCheckSecureStrategy.ProtectSpecificRoutes;

    private FirebaseAppCheckCondition firebaseAppCheckCondition;
    private FirebaseAppCheckCallback firebaseAppCheckCallback;
    private FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler;

    private FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService;

    private List<Route> routes = new ArrayList<>();

    public FirebaseAppCheckTokenVerifierService getFirebaseAppCheckTokenVerifierService() {
        return firebaseAppCheckTokenVerifierService;
    }

    public String getFirebaseProjectId() {
        return firebaseProjectId;
    }

    public String getFirebaseProjectNumber() {
        return firebaseProjectNumber;
    }

    public String getFirebaseAppCheckHeaderName() {
        return firebaseAppCheckHeaderName;
    }

    public String getFirebaseAppCheckApiBaseUrl() {
        return firebaseAppCheckApiBaseUrl;
    }

    public String getFirebaseAppCheckPublicKeyUrl() {
        return firebaseAppCheckPublicKeyUrl;
    }

    public FirebaseAppCheckCondition getAdditionalSecurityCheck() {
        return firebaseAppCheckCondition;
    }

    public FirebaseAppCheckCallback getAfterSecurityCheck() {
        return firebaseAppCheckCallback;
    }

    public FirebaseAppCheckErrorHandler getErrorBuilder() {
        return firebaseAppCheckErrorHandler;
    }

    public FirebaseAppCheckSecureStrategy getSecureStrategy() {
        return secureStrategy;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
