package com.github.danbel.spring.firebase.appcheck.exception.impl;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.properties.FirebaseAppCheckErrorMessages;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckFetchPublicKeyException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckFetchPublicKeyErrorType;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckVerifyJwtErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FirebaseAppCheckErrorHandlerImpl implements FirebaseAppCheckErrorHandler {

    private final FirebaseAppCheckErrorMessages firebaseAppCheckErrorMessages;

    public FirebaseAppCheckErrorHandlerImpl(FirebaseAppCheckErrorMessages firebaseAppCheckErrorMessages) {
        this.firebaseAppCheckErrorMessages = firebaseAppCheckErrorMessages;
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
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getSigningKeyNotFound());
                    }
                    case NETWORK_ERROR -> {
                        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                        writeTextError(response, firebaseAppCheckErrorMessages.getNetworkError());
                    }
                    case RATE_LIMIT_REACHED -> {
                        response.setStatus(429);
                        writeTextError(response, firebaseAppCheckErrorMessages.getRateLimitReached());
                    }
                    case JWK_PROCESSING_ERROR -> {
                        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                        writeTextError(response, firebaseAppCheckErrorMessages.getJwkProcessingError());
                    }
                    case UNKNOWN_ERROR -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        writeTextError(response, firebaseAppCheckErrorMessages.getUnknownPublicKeyError());
                    }
                }
            } else if (e instanceof FirebaseAppCheckVerifyJwtException verifyJwtException) {
                FirebaseAppCheckVerifyJwtErrorType errorType = verifyJwtException.getErrorType();
                switch (errorType) {
                    case TOKEN_EXPIRED -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getTokenExpired());
                    }
                    case INVALID_TOKEN -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getInvalidToken());
                    }
                    case INVALID_TOKEN_TYPE -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getInvalidTokenType());
                    }
                    case INVALID_SIGNATURE -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getInvalidSignature());
                    }
                    case MISSING_REQUIRED_CLAIM -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getMissingRequiredClaim());
                    }
                    case INVALID_CLAIM_VALUE -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        writeTextError(response, firebaseAppCheckErrorMessages.getInvalidClaimValue());
                    }
                    case UNSUPPORTED_ALGORITHM -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        writeTextError(response, firebaseAppCheckErrorMessages.getUnsupportedAlgorithm());
                    }
                    case GENERIC_JWT_VERIFICATION_ERROR -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        writeTextError(response, firebaseAppCheckErrorMessages.getGenericJwtVerificationError());
                    }
                    case JWT_VERIFICATION_FAILED -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        writeTextError(response, firebaseAppCheckErrorMessages.getJwtVerificationFailed());
                    }
                    case UNKNOWN_ERROR -> {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        writeTextError(response, firebaseAppCheckErrorMessages.getUnknownVerifyJwtError());
                    }
                }
            } else if (e instanceof FirebaseAppCheckException) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writeTextError(response, firebaseAppCheckErrorMessages.getGenericUnauthorized());
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writeTextError(response, firebaseAppCheckErrorMessages.getGenericInternalError());
            }

        } catch (IOException ignored) {}

        return false;
    }

    private void writeTextError(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
    }
}