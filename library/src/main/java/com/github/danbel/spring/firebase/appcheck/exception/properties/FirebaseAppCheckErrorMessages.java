package com.github.danbel.spring.firebase.appcheck.exception.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "firebase.app-check.error.messages")
public class FirebaseAppCheckErrorMessages {
    private String signingKeyNotFound = "Signing key not found for Firebase App Check token.";
    private String networkError = "Network error while fetching Firebase App Check public key.";
    private String rateLimitReached = "Rate limit reached while fetching Firebase App Check keys.";
    private String jwkProcessingError = "Failed to process JWK for Firebase App Check.";
    private String unknownPublicKeyError = "Unknown error occurred while fetching Firebase App Check key.";

    private String tokenExpired = "Firebase App Check token has expired.";
    private String invalidToken = "Firebase App Check token is invalid.";
    private String invalidTokenType = "Invalid token type provided for Firebase App Check.";
    private String unsupportedAlgorithm = "Unsupported algorithm used in Firebase App Check token.";
    private String invalidSignature = "Invalid signature detected in Firebase App Check token.";
    private String missingRequiredClaim = "Missing required claim in Firebase App Check token.";
    private String invalidClaimValue = "Invalid claim value found in Firebase App Check token.";
    private String genericJwtVerificationError = "Generic JWT verification error in Firebase App Check.";
    private String jwtVerificationFailed = "JWT verification failed in Firebase App Check.";
    private String unknownVerifyJwtError = "Unknown error occurred during Firebase App Check token verification.";

    private String genericUnauthorized = "Unauthorized Firebase App Check request.";
    private String genericInternalError = "Internal server error in Firebase App Check.";

    public String getSigningKeyNotFound() {
        return signingKeyNotFound;
    }

    public String getNetworkError() {
        return networkError;
    }

    public String getRateLimitReached() {
        return rateLimitReached;
    }

    public String getJwkProcessingError() {
        return jwkProcessingError;
    }

    public String getUnknownPublicKeyError() {
        return unknownPublicKeyError;
    }

    public String getTokenExpired() {
        return tokenExpired;
    }

    public String getInvalidToken() {
        return invalidToken;
    }

    public String getInvalidTokenType() {
        return invalidTokenType;
    }

    public String getUnsupportedAlgorithm() {
        return unsupportedAlgorithm;
    }

    public String getInvalidSignature() {
        return invalidSignature;
    }

    public String getMissingRequiredClaim() {
        return missingRequiredClaim;
    }

    public String getInvalidClaimValue() {
        return invalidClaimValue;
    }

    public String getGenericJwtVerificationError() {
        return genericJwtVerificationError;
    }

    public String getJwtVerificationFailed() {
        return jwtVerificationFailed;
    }

    public String getUnknownVerifyJwtError() {
        return unknownVerifyJwtError;
    }

    public String getGenericUnauthorized() {
        return genericUnauthorized;
    }

    public String getGenericInternalError() {
        return genericInternalError;
    }

    public void setSigningKeyNotFound(String signingKeyNotFound) {
        this.signingKeyNotFound = signingKeyNotFound;
    }

    public void setNetworkError(String networkError) {
        this.networkError = networkError;
    }

    public void setRateLimitReached(String rateLimitReached) {
        this.rateLimitReached = rateLimitReached;
    }

    public void setJwkProcessingError(String jwkProcessingError) {
        this.jwkProcessingError = jwkProcessingError;
    }

    public void setUnknownPublicKeyError(String unknownPublicKeyError) {
        this.unknownPublicKeyError = unknownPublicKeyError;
    }

    public void setTokenExpired(String tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public void setInvalidToken(String invalidToken) {
        this.invalidToken = invalidToken;
    }

    public void setInvalidTokenType(String invalidTokenType) {
        this.invalidTokenType = invalidTokenType;
    }

    public void setUnsupportedAlgorithm(String unsupportedAlgorithm) {
        this.unsupportedAlgorithm = unsupportedAlgorithm;
    }

    public void setInvalidSignature(String invalidSignature) {
        this.invalidSignature = invalidSignature;
    }

    public void setMissingRequiredClaim(String missingRequiredClaim) {
        this.missingRequiredClaim = missingRequiredClaim;
    }

    public void setInvalidClaimValue(String invalidClaimValue) {
        this.invalidClaimValue = invalidClaimValue;
    }

    public void setGenericJwtVerificationError(String genericJwtVerificationError) {
        this.genericJwtVerificationError = genericJwtVerificationError;
    }

    public void setJwtVerificationFailed(String jwtVerificationFailed) {
        this.jwtVerificationFailed = jwtVerificationFailed;
    }

    public void setUnknownVerifyJwtError(String unknownVerifyJwtError) {
        this.unknownVerifyJwtError = unknownVerifyJwtError;
    }

    public void setGenericUnauthorized(String genericUnauthorized) {
        this.genericUnauthorized = genericUnauthorized;
    }

    public void setGenericInternalError(String genericInternalError) {
        this.genericInternalError = genericInternalError;
    }
}