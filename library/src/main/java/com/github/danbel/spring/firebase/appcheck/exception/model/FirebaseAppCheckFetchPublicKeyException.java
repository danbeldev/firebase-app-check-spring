package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;

public class FirebaseAppCheckFetchPublicKeyException extends FirebaseAppCheckException {

    public FirebaseAppCheckFetchPublicKeyException(String message, FirebaseAppCheckErrorType errorType) {
        super(message, errorType);
    }
}
