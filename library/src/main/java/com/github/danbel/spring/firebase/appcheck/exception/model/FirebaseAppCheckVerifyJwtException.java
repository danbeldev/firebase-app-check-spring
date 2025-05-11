package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;

public class FirebaseAppCheckVerifyJwtException extends FirebaseAppCheckException {

    public FirebaseAppCheckVerifyJwtException(String message, FirebaseAppCheckErrorType errorType) {
        super(message, errorType);
    }
}
