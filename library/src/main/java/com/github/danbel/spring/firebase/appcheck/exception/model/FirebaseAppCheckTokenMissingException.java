package com.github.danbel.spring.firebase.appcheck.exception.model;

/**
 * Выбрасывается, когда обязательный токен Firebase App Check отсутствует в запросе.
 * <p>
 * Это исключение указывает, что запрос не содержит токен App Check в ожидаемом заголовке
 * ({@code FirebaseAppCheckProperties.getHeaderName()}), когда токен был обязателен для данного эндпоинта.
 * @see FirebaseAppCheckException
 * @see com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties#getHeaderName()
 */
public class FirebaseAppCheckTokenMissingException extends FirebaseAppCheckException {

    public FirebaseAppCheckTokenMissingException() {
        super();
    }
}
