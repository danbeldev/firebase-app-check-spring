package com.github.danbel.spring.firebase.appcheck.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface FirebaseAppCheckErrorHandler {

    boolean handle(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    );
}
