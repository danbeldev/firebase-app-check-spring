package com.github.danbel.spring.firebase.appcheck.services;

import com.github.danbel.spring.firebase.appcheck.error.exceptions.FirebaseAppCheckFetchPublicKeyException;
import com.github.danbel.spring.firebase.appcheck.error.exceptions.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.properties.DecodedJwt;

public interface FirebaseAppCheckTokenVerifierService {

    DecodedJwt verifyFirebaseAppCheckToken(
            String firebaseAppCheckTokenJwt,
            String firebaseProjectId,
            String firebaseProjectNumber,
            String issuerBaseUrl,
            String publicKeyUrl
    ) throws FirebaseAppCheckVerifyJwtException, FirebaseAppCheckFetchPublicKeyException;
}
