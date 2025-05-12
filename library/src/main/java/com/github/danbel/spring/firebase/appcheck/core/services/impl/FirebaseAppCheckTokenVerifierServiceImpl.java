package com.github.danbel.spring.firebase.appcheck.core.services.impl;

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.danbel.spring.firebase.appcheck.config.security.FetchFirebaseAppCheckPublicKeyConfig;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckFetchPublicKeyException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckVerifyJwtException;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckFetchPublicKeyErrorType;
import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckVerifyJwtErrorType;
import com.github.danbel.spring.firebase.appcheck.model.DecodedJwt;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;

import java.net.URL;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

/**
 * Сервис верификации токена Firebase App Check.
 * Выполняет проверку подписи и валидности JWT-токена, полученного с клиента.
 */
public class FirebaseAppCheckTokenVerifierServiceImpl implements FirebaseAppCheckTokenVerifierService {

    private final FetchFirebaseAppCheckPublicKeyConfig fetchPublicKeyConfig;

    public FirebaseAppCheckTokenVerifierServiceImpl() {
        fetchPublicKeyConfig = new FetchFirebaseAppCheckPublicKeyConfig();
    }

    /***
     * @param fetchPublicKeyConfig конфигурация для получения публичного ключа Firebase App Check.
     */
    public FirebaseAppCheckTokenVerifierServiceImpl(FetchFirebaseAppCheckPublicKeyConfig fetchPublicKeyConfig) {
        this.fetchPublicKeyConfig = fetchPublicKeyConfig;
    }

    /**
     * Проверяет подлинность токена Firebase App Check, извлекая публичный ключ с удалённого адреса.
     *
     * @param tokenJwt      строка JWT-токена.
     * @param projectId     идентификатор проекта Firebase.
     * @param projectNumber числовой идентификатор проекта Firebase.
     * @param issuerBaseUrl базовый URL для формирования ожидаемого `issuer`.
     * @param publicKeyUrl  URL для загрузки публичного ключа Firebase.
     * @return декодированный JWT, обёрнутый в {@link DecodedJwt}.
     * @throws FirebaseAppCheckVerifyJwtException при любой ошибке верификации токена.
     */
    @Override
    public DecodedJwt verifyFirebaseAppCheckToken(
            String tokenJwt,
            String projectId,
            String projectNumber,
            String issuerBaseUrl,
            String publicKeyUrl
    ) {
        PublicKey publicKey = fetchFirebaseAppCheckPublicKey(tokenJwt, publicKeyUrl);
        return verifyFirebaseAppCheckToken(tokenJwt, publicKey, projectId, projectNumber, issuerBaseUrl);
    }

    /**
     * Загружает публичный ключ Firebase App Check по заданному URL.
     * Используется заголовок `kid` из JWT для выбора нужного ключа.
     *
     * @param jwtString строка JWT-токена.
     * @param url       URL до JWKS (JSON Web Key Set).
     * @return публичный ключ, необходимый для проверки подписи JWT.
     */
    private PublicKey fetchFirebaseAppCheckPublicKey(String jwtString, String url) {
        try {
            var cacheConfig = fetchPublicKeyConfig.cacheConfiguration();
            var rateLimitedConfig = fetchPublicKeyConfig.rateLimitedConfig();

            JwkProvider jwkProvider = new JwkProviderBuilder(new URL(url))
                    .cached(
                            cacheConfig.getCacheSize(),
                            Duration.ofMillis(cacheConfig.getExpiresIn().toMillis())
                    )
                    .rateLimited(rateLimitedConfig.isEnabled())
                    .build();

            DecodedJWT decodedJwt = JWT.decode(jwtString);
            String kid = decodedJwt.getHeaderClaim("kid").asString();

            return jwkProvider.get(kid).getPublicKey();

        } catch (NetworkException e) {
            throw new FirebaseAppCheckFetchPublicKeyException(
                    e.getMessage(),
                    FirebaseAppCheckFetchPublicKeyErrorType.NETWORK_ERROR
            );
        } catch (SigningKeyNotFoundException e) {
            throw new FirebaseAppCheckFetchPublicKeyException(
                    e.getMessage(),
                    FirebaseAppCheckFetchPublicKeyErrorType.SIGNING_KEY_NOT_FOUND
            );
        } catch (RateLimitReachedException e) {
            throw new FirebaseAppCheckFetchPublicKeyException(
                    e.getMessage(),
                    FirebaseAppCheckFetchPublicKeyErrorType.RATE_LIMIT_REACHED
            );
        } catch (JwkException e) {
            throw new FirebaseAppCheckFetchPublicKeyException(
                    e.getMessage(),
                    FirebaseAppCheckFetchPublicKeyErrorType.JWK_PROCESSING_ERROR
            );
        } catch (JWTDecodeException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_TOKEN
            );
        } catch (AlgorithmMismatchException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.UNSUPPORTED_ALGORITHM
            );
        } catch (SignatureVerificationException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_SIGNATURE
            );
        } catch (MissingClaimException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.MISSING_REQUIRED_CLAIM
            );
        } catch (IncorrectClaimException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_CLAIM_VALUE
            );
        } catch (JWTVerificationException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.JWT_VERIFICATION_FAILED
            );
        } catch (Exception e) {
            throw new FirebaseAppCheckFetchPublicKeyException(
                    e.getMessage(),
                    FirebaseAppCheckFetchPublicKeyErrorType.UNKNOWN_ERROR
            );
        }
    }

    /**
     * Проверяет подпись и содержание JWT-токена Firebase App Check.
     *
     * @param jwtString     строка JWT.
     * @param publicKey     публичный ключ для верификации подписи.
     * @param projectId     ID проекта Firebase.
     * @param projectNumber числовой ID проекта Firebase.
     * @param issuerBaseUrl базовый адрес ожидаемого отправителя (`issuer`).
     * @return обёрнутый декодированный JWT.
     */
    private DecodedJwt verifyFirebaseAppCheckToken(
            String jwtString,
            PublicKey publicKey,
            String projectId,
            String projectNumber,
            String issuerBaseUrl
    ) {
        try {
            JWTVerifier verifier = JWT
                    .require(Algorithm.RSA256((RSAPublicKey) publicKey, null))
                    .withAnyOfAudience("projects/" + projectNumber, "projects/" + projectId)
                    .withIssuer(issuerBaseUrl + "/" + projectNumber)
                    .build();

            DecodedJWT decodedJwt = verifier.verify(jwtString);
            String tokenHeader = decodedJwt.getHeaderClaim("typ").asString();

            if (!"JWT".equals(tokenHeader)) {
                throw new FirebaseAppCheckVerifyJwtException(
                        "The token header of value " + tokenHeader + " is not equal to 'JWT'",
                        FirebaseAppCheckVerifyJwtErrorType.INVALID_TOKEN_TYPE
                );
            }

            return new DecodedJwt(decodedJwt.getToken());

        } catch (TokenExpiredException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.TOKEN_EXPIRED
            );
        } catch (JWTDecodeException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_TOKEN
            );
        } catch (AlgorithmMismatchException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.UNSUPPORTED_ALGORITHM
            );
        } catch (SignatureVerificationException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_SIGNATURE
            );
        } catch (MissingClaimException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.MISSING_REQUIRED_CLAIM
            );
        } catch (IncorrectClaimException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.INVALID_CLAIM_VALUE
            );
        } catch (JWTVerificationException e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.JWT_VERIFICATION_FAILED
            );
        } catch (Exception e) {
            throw new FirebaseAppCheckVerifyJwtException(
                    e.getMessage(),
                    FirebaseAppCheckVerifyJwtErrorType.UNKNOWN_ERROR
            );
        }
    }
}
