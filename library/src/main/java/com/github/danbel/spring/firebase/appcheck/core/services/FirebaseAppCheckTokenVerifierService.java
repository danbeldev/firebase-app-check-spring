package com.github.danbel.spring.firebase.appcheck.core.services;

import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckFetchPublicKeyException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.model.DecodedJwt;

public interface FirebaseAppCheckTokenVerifierService {

    DecodedJwt verifyFirebaseAppCheckToken(
            String firebaseAppCheckTokenJwt,
            String firebaseProjectId,
            String firebaseProjectNumber,
            String issuerBaseUrl,
            String publicKeyUrl
    ) throws FirebaseAppCheckVerifyJwtException, FirebaseAppCheckFetchPublicKeyException;
}
