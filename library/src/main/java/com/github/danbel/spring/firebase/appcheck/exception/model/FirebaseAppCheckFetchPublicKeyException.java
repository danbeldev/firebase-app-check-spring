package com.github.danbel.spring.firebase.appcheck.exception.model;

import com.github.danbel.spring.firebase.appcheck.exception.types.FirebaseAppCheckFetchPublicKeyErrorType;

/**
 * Исключение, возникающее при ошибке получения публичного ключа Firebase App Check.
 */
public class FirebaseAppCheckFetchPublicKeyException extends FirebaseAppCheckException {

    private final FirebaseAppCheckFetchPublicKeyErrorType errorType;

    /**
     * Создаёт исключение с сообщением и типом ошибки получения публичного ключа.
     *
     * @param message   описание ошибки
     * @param errorType тип ошибки получения публичного ключа
     */
    public FirebaseAppCheckFetchPublicKeyException(String message, FirebaseAppCheckFetchPublicKeyErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Возвращает тип ошибки получения публичного ключа.
     *
     * @return тип ошибки {@link FirebaseAppCheckFetchPublicKeyErrorType}
     */
    public FirebaseAppCheckFetchPublicKeyErrorType getErrorType() {
        return errorType;
    }
}