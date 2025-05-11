package com.github.danbel.spring.firebase.appcheck.exception.impl;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirebaseAppCheckErrorHandlerImpl implements FirebaseAppCheckErrorHandler {
    @Override
    public boolean handle(
            Exception e,
            FirebaseAppCheckErrorType errorType,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
