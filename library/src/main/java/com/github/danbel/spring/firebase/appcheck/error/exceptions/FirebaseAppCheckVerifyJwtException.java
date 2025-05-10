package com.github.danbel.spring.firebase.appcheck.error.exceptions;

import com.github.danbel.spring.firebase.appcheck.error.types.FirebaseAppCheckErrorType;

public class FirebaseAppCheckVerifyJwtException extends FirebaseAppCheckException {

    public FirebaseAppCheckVerifyJwtException(String message, FirebaseAppCheckErrorType errorType) {
        super(message, errorType);
    }
}
