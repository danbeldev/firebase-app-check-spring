package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckVerifyJwtErrorType;

/**
 * Исключение, возникающее при ошибке верификации JWT токена Firebase App Check.
 */
public class FirebaseAppCheckVerifyJwtException extends FirebaseAppCheckException {

    private final FirebaseAppCheckVerifyJwtErrorType errorType;

    /**
     * Создаёт исключение с сообщением и типом ошибки верификации JWT токена.
     *
     * @param message   описание ошибки
     * @param errorType тип ошибки верификации JWT токена
     */
    public FirebaseAppCheckVerifyJwtException(String message, FirebaseAppCheckVerifyJwtErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Возвращает тип ошибки верификации JWT токена.
     *
     * @return тип ошибки {@link FirebaseAppCheckVerifyJwtErrorType}
     */
    public FirebaseAppCheckVerifyJwtErrorType getErrorType() {
        return errorType;
    }
}