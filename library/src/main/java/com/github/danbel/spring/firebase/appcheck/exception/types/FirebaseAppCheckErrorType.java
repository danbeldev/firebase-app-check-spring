package com.github.danbel.spring.firebase.appcheck.exception.types;

public enum FirebaseAppCheckErrorType {
    MissingAppCheckToken,
    CustomSecurityConditionFailed,
    TokenExpired,
    GenericJwtVerificationError,
    TokenIsNotValid,
    HeaderTypeIsNotJwt,
    TokenAlgorithmIsNotCorrect,
    TokenSignatureVerificationInvalid,
    TokenMissingClaim,
    TokenIncorrectClaim,
    SigningKeyNotFound,
    NetworkError,
    RateLimitReached,
    JwkError,
    UnknownError
}
