package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;

/**
 * Выбрасывается, когда дополнительные проверки безопасности, настроенные в {@code FirebaseAppCheckProperties},
 * не проходят при валидации токена.
 * <p>
 * Это исключение указывает, что хотя сам токен Firebase App Check был валидным,
 * он не удовлетворяет дополнительным кастомным требованиям безопасности приложения.
 * </p>
 * <p>
 * @see FirebaseAppCheckException
 * @see FirebaseAppCheckProperties#getAdditionalSecurityCheck()
 */
public class FirebaseAppCheckAdditionalSecurityCheckException extends FirebaseAppCheckException {

    public FirebaseAppCheckAdditionalSecurityCheckException() {
        super();
    }
}