package com.github.danbel.spring.firebase.appcheck.exception.types;

/**
 * Типы ошибок при получении публичного ключа Firebase App Check
 */
public enum FirebaseAppCheckFetchPublicKeyErrorType {
    /**
     * Ключ подписи не найден (неверный kid в заголовке токена)
     */
    SIGNING_KEY_NOT_FOUND,

    /**
     * Ошибка сети при запросе ключей
     */
    NETWORK_ERROR,

    /**
     * Превышен лимит запросов ключей
     */
    RATE_LIMIT_REACHED,

    /**
     * Ошибка обработки JWK (JSON Web Key)
     */
    JWK_PROCESSING_ERROR,

    /**
     * Неизвестная ошибка при получении ключа
     */
    UNKNOWN_ERROR
}
