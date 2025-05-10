package com.github.danbel.spring.firebase.appcheck.properties;

/**
 * Определяет callback, который будет выполнен после успешной верификации
 * токена Firebase App Check и прохождения всех дополнительных проверок.
 * <p>
 * Может использоваться, например, для логирования, сбора метрик или других побочных действий.
 */
@FunctionalInterface
public interface FirebaseAppCheckCallback {

    /**
     * Выполняет действие после успешной проверки безопасности.
     *
     * @param decodedJwt декодированный и верифицированный токен Firebase App Check
     */
    void execute(DecodedJwt decodedJwt);
}