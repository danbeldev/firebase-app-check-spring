package com.github.danbel.spring.firebase.appcheck.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckErrorType;
import com.github.danbel.spring.firebase.appcheck.model.DecodedJwt;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;

public class FirebaseAppCheckTokenVerifierServiceMock implements FirebaseAppCheckTokenVerifierService {
    @Override
    public DecodedJwt verifyFirebaseAppCheckToken(
            String firebaseAppCheckTokenJwt,
            String firebaseProjectId,
            String firebaseProjectNumber,
            String issuerBaseUrl,
            String publicKeyUrl
    ) throws FirebaseAppCheckVerifyJwtException {
        try {
            DecodedJWT verified = JWT.decode(firebaseAppCheckTokenJwt);

            if (!verified.getAudience().get(0).equals("projects/" + firebaseProjectNumber)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "The " + verified.getAudience().get(0) + " is not equal to projects/" + firebaseProjectNumber,
                        FirebaseAppCheckErrorType.GenericJwtVerificationError
                );
            }

            if (!verified.getAudience().get(1).equals("projects/" + firebaseProjectId)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "The " + verified.getAudience().get(1) + " is not equal to projects/" + firebaseProjectId,
                        FirebaseAppCheckErrorType.GenericJwtVerificationError
                );
            }

            if (!verified.getIssuer().equals("https://firebaseappcheck.googleapis.com/" + firebaseProjectNumber)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "The " + verified.getIssuer() + " is not equal to projects/" + firebaseProjectNumber,
                        FirebaseAppCheckErrorType.GenericJwtVerificationError
                );
            }

            return new DecodedJwt(verified.getToken());
        } catch (JWTDecodeException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    "Token is not valid: " + e,
                    FirebaseAppCheckErrorType.TokenIsNotValid
            );
        }
    }
}
