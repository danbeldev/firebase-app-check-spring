package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;

public class FirebaseAppCheckException extends RuntimeException {

    public final FirebaseAppCheckErrorType errorType;

    public FirebaseAppCheckException(String message, FirebaseAppCheckErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public FirebaseAppCheckErrorType getErrorType() {
        return errorType;
    }
}
