package com.github.danbel.spring.firebase.appcheck.exception.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "firebase.app-check.error")
public class FirebaseAppCheckErrorProperties {

    public static class ErrorConfig {
        private int status;
        private String message;

        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    private ErrorConfig signingKeyNotFound = new ErrorConfig();
    private ErrorConfig networkError = new ErrorConfig();
    private ErrorConfig rateLimitReached = new ErrorConfig();
    private ErrorConfig jwkProcessingError = new ErrorConfig();
    private ErrorConfig unknownPublicKeyError = new ErrorConfig();

    private ErrorConfig tokenExpired = new ErrorConfig();
    private ErrorConfig invalidToken = new ErrorConfig();
    private ErrorConfig invalidTokenType = new ErrorConfig();
    private ErrorConfig invalidSignature = new ErrorConfig();
    private ErrorConfig missingRequiredClaim = new ErrorConfig();
    private ErrorConfig invalidClaimValue = new ErrorConfig();
    private ErrorConfig unsupportedAlgorithm = new ErrorConfig();
    private ErrorConfig genericJwtVerificationError = new ErrorConfig();
    private ErrorConfig jwtVerificationFailed = new ErrorConfig();
    private ErrorConfig unknownVerifyJwtError = new ErrorConfig();

    private ErrorConfig genericUnauthorized = new ErrorConfig();
    private ErrorConfig genericInternalError = new ErrorConfig();

    public FirebaseAppCheckErrorProperties() {
        signingKeyNotFound.setStatus(401);
        signingKeyNotFound.setMessage("Signing key not found for Firebase App Check token.");

        networkError.setStatus(502);
        networkError.setMessage("Network error while fetching Firebase App Check public key.");

        rateLimitReached.setStatus(429);
        rateLimitReached.setMessage("Rate limit reached while fetching Firebase App Check keys.");

        jwkProcessingError.setStatus(502);
        jwkProcessingError.setMessage("Failed to process JWK for Firebase App Check.");

        unknownPublicKeyError.setStatus(500);
        unknownPublicKeyError.setMessage("Unknown error occurred while fetching Firebase App Check key.");

        tokenExpired.setStatus(401);
        tokenExpired.setMessage("Firebase App Check token has expired.");

        invalidToken.setStatus(401);
        invalidToken.setMessage("Firebase App Check token is invalid.");

        invalidTokenType.setStatus(401);
        invalidTokenType.setMessage("Invalid token type provided for Firebase App Check.");

        invalidSignature.setStatus(401);
        invalidSignature.setMessage("Invalid signature detected in Firebase App Check token.");

        missingRequiredClaim.setStatus(401);
        missingRequiredClaim.setMessage("Missing required claim in Firebase App Check token.");

        invalidClaimValue.setStatus(401);
        invalidClaimValue.setMessage("Invalid claim value found in Firebase App Check token.");

        unsupportedAlgorithm.setStatus(500);
        unsupportedAlgorithm.setMessage("Unsupported algorithm used in Firebase App Check token.");

        genericJwtVerificationError.setStatus(500);
        genericJwtVerificationError.setMessage("Generic JWT verification error in Firebase App Check.");

        jwtVerificationFailed.setStatus(500);
        jwtVerificationFailed.setMessage("JWT verification failed in Firebase App Check.");

        unknownVerifyJwtError.setStatus(500);
        unknownVerifyJwtError.setMessage("Unknown error occurred during Firebase App Check token verification.");

        genericUnauthorized.setStatus(401);
        genericUnauthorized.setMessage("Unauthorized Firebase App Check request.");

        genericInternalError.setStatus(500);
        genericInternalError.setMessage("Internal server error in Firebase App Check.");
    }

    public ErrorConfig getSigningKeyNotFound() {
        return signingKeyNotFound;
    }

    public ErrorConfig getNetworkError() {
        return networkError;
    }

    public ErrorConfig getRateLimitReached() {
        return rateLimitReached;
    }

    public ErrorConfig getJwkProcessingError() {
        return jwkProcessingError;
    }

    public ErrorConfig getUnknownPublicKeyError() {
        return unknownPublicKeyError;
    }

    public ErrorConfig getTokenExpired() {
        return tokenExpired;
    }

    public ErrorConfig getInvalidToken() {
        return invalidToken;
    }

    public ErrorConfig getInvalidTokenType() {
        return invalidTokenType;
    }

    public ErrorConfig getInvalidSignature() {
        return invalidSignature;
    }

    public ErrorConfig getMissingRequiredClaim() {
        return missingRequiredClaim;
    }

    public ErrorConfig getInvalidClaimValue() {
        return invalidClaimValue;
    }

    public ErrorConfig getUnsupportedAlgorithm() {
        return unsupportedAlgorithm;
    }

    public ErrorConfig getGenericJwtVerificationError() {
        return genericJwtVerificationError;
    }

    public ErrorConfig getJwtVerificationFailed() {
        return jwtVerificationFailed;
    }

    public ErrorConfig getUnknownVerifyJwtError() {
        return unknownVerifyJwtError;
    }

    public ErrorConfig getGenericUnauthorized() {
        return genericUnauthorized;
    }

    public ErrorConfig getGenericInternalError() {
        return genericInternalError;
    }

    public void setSigningKeyNotFound(ErrorConfig signingKeyNotFound) {
        this.signingKeyNotFound = signingKeyNotFound;
    }

    public void setNetworkError(ErrorConfig networkError) {
        this.networkError = networkError;
    }

    public void setRateLimitReached(ErrorConfig rateLimitReached) {
        this.rateLimitReached = rateLimitReached;
    }

    public void setJwkProcessingError(ErrorConfig jwkProcessingError) {
        this.jwkProcessingError = jwkProcessingError;
    }

    public void setUnknownPublicKeyError(ErrorConfig unknownPublicKeyError) {
        this.unknownPublicKeyError = unknownPublicKeyError;
    }

    public void setTokenExpired(ErrorConfig tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public void setInvalidToken(ErrorConfig invalidToken) {
        this.invalidToken = invalidToken;
    }

    public void setInvalidTokenType(ErrorConfig invalidTokenType) {
        this.invalidTokenType = invalidTokenType;
    }

    public void setInvalidSignature(ErrorConfig invalidSignature) {
        this.invalidSignature = invalidSignature;
    }

    public void setMissingRequiredClaim(ErrorConfig missingRequiredClaim) {
        this.missingRequiredClaim = missingRequiredClaim;
    }

    public void setInvalidClaimValue(ErrorConfig invalidClaimValue) {
        this.invalidClaimValue = invalidClaimValue;
    }

    public void setUnsupportedAlgorithm(ErrorConfig unsupportedAlgorithm) {
        this.unsupportedAlgorithm = unsupportedAlgorithm;
    }

    public void setGenericJwtVerificationError(ErrorConfig genericJwtVerificationError) {
        this.genericJwtVerificationError = genericJwtVerificationError;
    }

    public void setJwtVerificationFailed(ErrorConfig jwtVerificationFailed) {
        this.jwtVerificationFailed = jwtVerificationFailed;
    }

    public void setUnknownVerifyJwtError(ErrorConfig unknownVerifyJwtError) {
        this.unknownVerifyJwtError = unknownVerifyJwtError;
    }

    public void setGenericUnauthorized(ErrorConfig genericUnauthorized) {
        this.genericUnauthorized = genericUnauthorized;
    }

    public void setGenericInternalError(ErrorConfig genericInternalError) {
        this.genericInternalError = genericInternalError;
    }
}