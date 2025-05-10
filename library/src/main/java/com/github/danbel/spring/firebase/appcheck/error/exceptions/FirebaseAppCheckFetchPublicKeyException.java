package com.github.danbel.spring.firebase.appcheck.error.exceptions;

import com.github.danbel.spring.firebase.appcheck.error.types.FirebaseAppCheckErrorType;

public class FirebaseAppCheckFetchPublicKeyException extends FirebaseAppCheckException {

    public FirebaseAppCheckFetchPublicKeyException(String message, FirebaseAppCheckErrorType errorType) {
        super(message, errorType);
    }
}
