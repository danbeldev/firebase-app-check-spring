package com.github.danbel.spring.firebase.appcheck.exception.impl;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.properties.FirebaseAppCheckErrorProperties;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckFetchPublicKeyException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckFetchPublicKeyErrorType;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckVerifyJwtErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FirebaseAppCheckErrorHandlerImpl implements FirebaseAppCheckErrorHandler {

    private final FirebaseAppCheckErrorProperties firebaseAppCheckErrorProperties;

    public FirebaseAppCheckErrorHandlerImpl(FirebaseAppCheckErrorProperties firebaseAppCheckErrorProperties) {
        this.firebaseAppCheckErrorProperties = firebaseAppCheckErrorProperties;
    }

    @Override
    public boolean handle(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        try {
            response.setContentType("text/plain");

            if (e instanceof FirebaseAppCheckFetchPublicKeyException fetchPublicKeyException) {
                FirebaseAppCheckFetchPublicKeyErrorType errorType = fetchPublicKeyException.getErrorType();
                switch (errorType) {
                    case SIGNING_KEY_NOT_FOUND -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getSigningKeyNotFound().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getSigningKeyNotFound().getMessage());
                    }
                    case NETWORK_ERROR -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getNetworkError().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getNetworkError().getMessage());
                    }
                    case RATE_LIMIT_REACHED -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getRateLimitReached().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getRateLimitReached().getMessage());
                    }
                    case JWK_PROCESSING_ERROR -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getJwkProcessingError().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getJwkProcessingError().getMessage());
                    }
                    case UNKNOWN_ERROR -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getUnknownPublicKeyError().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getUnknownPublicKeyError().getMessage());
                    }
                }
            } else if (e instanceof FirebaseAppCheckVerifyJwtException verifyJwtException) {
                FirebaseAppCheckVerifyJwtErrorType errorType = verifyJwtException.getErrorType();
                switch (errorType) {
                    case TOKEN_EXPIRED -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getTokenExpired().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getTokenExpired().getMessage());
                    }
                    case INVALID_TOKEN -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getInvalidToken().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getInvalidToken().getMessage());
                    }
                    case INVALID_TOKEN_TYPE -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getInvalidTokenType().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getInvalidTokenType().getMessage());
                    }
                    case INVALID_SIGNATURE -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getInvalidSignature().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getInvalidSignature().getMessage());
                    }
                    case MISSING_REQUIRED_CLAIM -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getMissingRequiredClaim().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getMissingRequiredClaim().getMessage());
                    }
                    case INVALID_CLAIM_VALUE -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getInvalidClaimValue().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getInvalidClaimValue().getMessage());
                    }
                    case UNSUPPORTED_ALGORITHM -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getUnsupportedAlgorithm().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getUnsupportedAlgorithm().getMessage());
                    }
                    case GENERIC_JWT_VERIFICATION_ERROR -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getGenericJwtVerificationError().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getGenericJwtVerificationError().getMessage());
                    }
                    case JWT_VERIFICATION_FAILED -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getJwtVerificationFailed().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getJwtVerificationFailed().getMessage());
                    }
                    case UNKNOWN_ERROR -> {
                        response.setStatus(firebaseAppCheckErrorProperties.getUnknownVerifyJwtError().getStatus());
                        writeTextError(response, firebaseAppCheckErrorProperties.getUnknownVerifyJwtError().getMessage());
                    }
                }
            } else if (e instanceof FirebaseAppCheckException) {
                response.setStatus(firebaseAppCheckErrorProperties.getGenericUnauthorized().getStatus());
                writeTextError(response, firebaseAppCheckErrorProperties.getGenericUnauthorized().getMessage());
            } else {
                response.setStatus(firebaseAppCheckErrorProperties.getGenericInternalError().getStatus());
                writeTextError(response, firebaseAppCheckErrorProperties.getGenericInternalError().getMessage());
            }

        } catch (IOException ignored) {}

        return false;
    }

    private void writeTextError(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
    }
}