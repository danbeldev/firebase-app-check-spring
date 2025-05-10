package com.github.danbel.spring.firebase.appcheck.interceptors;

import com.github.danbel.spring.firebase.appcheck.annotations.RequireFirebaseAppCheck;
import com.github.danbel.spring.firebase.appcheck.error.exceptions.FirebaseAppCheckException;
import com.github.danbel.spring.firebase.appcheck.error.types.FirebaseAppCheckErrorType;
import com.github.danbel.spring.firebase.appcheck.properties.FirebaseAppCheckProperties;
import com.github.danbel.spring.firebase.appcheck.properties.FirebaseAppCheckSecureStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class FirebaseAppCheckInterceptor implements HandlerInterceptor {

    private final FirebaseAppCheckProperties firebaseAppCheckConfiguration;

    public FirebaseAppCheckInterceptor(FirebaseAppCheckProperties firebaseAppCheckConfiguration) {
        this.firebaseAppCheckConfiguration = firebaseAppCheckConfiguration;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (enableFirebaseAppCheck(handler)) {
            String appCheckToken = request.getHeader(firebaseAppCheckConfiguration.getFirebaseAppCheckHeaderName());
            if (appCheckToken == null) {
                return firebaseAppCheckConfiguration.getErrorBuilder().handle(null, FirebaseAppCheckErrorType.MissingAppCheckToken, request, response, handler);
            }

            try {
                var verifiedJwt = firebaseAppCheckConfiguration.getFirebaseAppCheckTokenVerifierService()
                        .verifyFirebaseAppCheckToken(
                                appCheckToken,
                                firebaseAppCheckConfiguration.getFirebaseProjectId(),
                                firebaseAppCheckConfiguration.getFirebaseProjectNumber(),
                                firebaseAppCheckConfiguration.getFirebaseAppCheckApiBaseUrl(),
                                firebaseAppCheckConfiguration.getFirebaseAppCheckPublicKeyUrl()
                        );

                var isShouldContinue =
                        firebaseAppCheckConfiguration.getAdditionalSecurityCheck() != null ?
                                firebaseAppCheckConfiguration.getAdditionalSecurityCheck().evaluate(verifiedJwt) : true;

                if (!isShouldContinue) {
                    return firebaseAppCheckConfiguration.getErrorBuilder().handle(null, FirebaseAppCheckErrorType.CustomSecurityConditionFailed, request, response, handler);
                }

                if (firebaseAppCheckConfiguration.getAfterSecurityCheck() != null) {
                    firebaseAppCheckConfiguration.getAfterSecurityCheck().execute(verifiedJwt);
                }
            } catch (FirebaseAppCheckException e) {
                return firebaseAppCheckConfiguration.getErrorBuilder().handle(e, e.getErrorType(), request, response, handler);
            }
        }

        return true;
    }

    private Boolean enableFirebaseAppCheck(Object handler) {
        if (firebaseAppCheckConfiguration.getSecureStrategy() == FirebaseAppCheckSecureStrategy.ProtectAll) {
            return true;
        } else {
            if (handler instanceof HandlerMethod handlerMethod) {
                // check routes in conf
                return handlerMethod.getMethod().getAnnotation(RequireFirebaseAppCheck.class) != null;
            }
        }
        return false;
    }
}
