package com.github.danbel.spring.firebase.appcheck.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckVerifyJwtErrorType;
import com.github.danbel.spring.firebase.appcheck.model.DecodedJwt;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;

/**
 * Mock-реализация сервиса верификации токенов Firebase App Check для тестирования.
 * Выполняет базовую проверку структуры токена без реальной проверки подписи.
 */
public class FirebaseAppCheckTokenVerifierServiceMock implements FirebaseAppCheckTokenVerifierService {

    /**
     * Проверяет токен Firebase App Check (без проверки подписи)
     *
     * @param firebaseAppCheckTokenJwt JWT токен для проверки
     * @param firebaseProjectId ID проекта Firebase
     * @param firebaseProjectNumber номер проекта Firebase
     * @param issuerBaseUrl базовый URL эмитента
     * @param publicKeyUrl URL публичного ключа (не используется в mock)
     * @return декодированный токен
     * @throws FirebaseAppCheckVerifyJwtException если проверка не пройдена
     */
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

            // Проверка audience claims
            if (!verified.getAudience().contains("projects/" + firebaseProjectNumber)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "Missing required audience: projects/" + firebaseProjectNumber,
                        FirebaseAppCheckVerifyJwtErrorType.INVALID_CLAIM_VALUE
                );
            }

            if (!verified.getAudience().contains("projects/" + firebaseProjectId)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "Missing required audience: projects/" + firebaseProjectId,
                        FirebaseAppCheckVerifyJwtErrorType.INVALID_CLAIM_VALUE
                );
            }

            // Проверка issuer
            String expectedIssuer = issuerBaseUrl + "/" + firebaseProjectNumber;
            if (!expectedIssuer.equals(verified.getIssuer())) {
                throw new FirebaseAppCheckVerifyJwtException(
                        String.format("Invalid issuer. Expected: %s, actual: %s",
                                expectedIssuer, verified.getIssuer()),
                        FirebaseAppCheckVerifyJwtErrorType.INVALID_CLAIM_VALUE
                );
            }

            return new DecodedJwt(verified.getToken());

        } catch (JWTDecodeException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    "Invalid token format: " + e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_TOKEN
            );
        }
    }
}