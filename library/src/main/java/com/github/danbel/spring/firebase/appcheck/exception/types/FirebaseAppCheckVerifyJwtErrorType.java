package com.github.danbel.spring.firebase.appcheck.exception.types;

/**
 * Типы ошибок при верификации JWT токена Firebase App Check
 */
public enum FirebaseAppCheckVerifyJwtErrorType {
    /**
     * Токен просрочен (expired)
     */
    TOKEN_EXPIRED,

    /**
     * Токен имеет невалидный формат
     */
    INVALID_TOKEN,

    /**
     * Неверный тип токена (не JWT)
     */
    INVALID_TOKEN_TYPE,

    /**
     * Неподдерживаемый алгоритм подписи
     */
    UNSUPPORTED_ALGORITHM,

    /**
     * Невалидная подпись токена
     */
    INVALID_SIGNATURE,

    /**
     * Отсутствует обязательное поле (claim)
     */
    MISSING_REQUIRED_CLAIM,

    /**
     * Некорректное значение поля (claim)
     */
    INVALID_CLAIM_VALUE,

    /**
     * Общая ошибка верификации JWT
     */
    GENERIC_JWT_VERIFICATION_ERROR,

    /**
     * Общая ошибка проверки JWT, не подходящая под другие конкретные типы.
     * Используется как fallback для различных исключений при верификации JWT.
     */
    JWT_VERIFICATION_FAILED,

    /**
     * Непредвиденная или неклассифицированная ошибка при проверке токена.
     * Используется для ошибок, которые не подходят под другие категории.
     */
    UNKNOWN_ERROR
}
