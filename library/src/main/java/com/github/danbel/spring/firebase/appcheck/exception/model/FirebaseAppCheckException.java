package com.github.danbel.spring.firebase.appcheck.exception.model;

/**
 * Базовое исключение для ошибок, связанных с верификацией Firebase App Check.
 */
public class FirebaseAppCheckException extends RuntimeException {

    /**
     * Создаёт исключение без сообщения.
     */
    public FirebaseAppCheckException() {
        super();
    }

    /**
     * Создаёт исключение с указанным сообщением.
     *
     * @param message описание ошибки
     */
    public FirebaseAppCheckException(String message) {
        super(message);
    }
}