package com.github.danbel.spring.firebase.appcheck.model.properties;

import com.github.danbel.spring.firebase.appcheck.model.DecodedJwt;

/**
 * Представляет дополнительное условие безопасности, которое должно быть проверено
 * после верификации токена Firebase App Check.
 * <p>
 * Если условие не выполняется (возвращает false), запрос будет отклонён.
 */
@FunctionalInterface
public interface FirebaseAppCheckCondition {

    /**
     * Выполняет проверку пользовательского условия безопасности на основе верифицированного токена.
     *
     * @param decodedJwt декодированный и верифицированный токен Firebase App Check
     * @return true, если запрос можно пропустить дальше; false — если отклонить
     */
    Boolean evaluate(DecodedJwt decodedJwt);
}
