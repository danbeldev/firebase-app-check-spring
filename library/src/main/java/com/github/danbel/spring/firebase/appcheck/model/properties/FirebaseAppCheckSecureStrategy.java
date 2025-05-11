package com.github.danbel.spring.firebase.appcheck.model.properties;

/**
 * Стратегии применения проверки Firebase App Check к HTTP-запросам в приложении.
 * <p>
 * Перечисление определяет, при каких условиях запросы к серверу должны быть защищены
 * и проверяться на наличие валидного токена Firebase App Check.
 */
public enum FirebaseAppCheckSecureStrategy {

    /**
     * Применять Firebase App Check ко всем входящим HTTP-запросам.
     * <p>
     * Подходит для приложений, где необходимо обеспечить полную защиту всех эндпоинтов.
     */
    PROTECT_ALL,

    /**
     * Применять Firebase App Check только к методам, помеченным аннотацией {@code @RequireFirebaseAppCheck}.
     * <p>
     * Удобно для выборочной защиты отдельных методов контроллеров.
     */
    PROTECT_ANNOTATED,

    /**
     * Применять Firebase App Check только к маршрутам, явно указанным в конфигурации (через свойства).
     * <p>
     * Позволяет задавать защищённые пути через конфигурационный файл или Java-код.
     */
    PROTECT_ROUTES,

    /**
     * Применять Firebase App Check как к аннотированным методам, так и к маршрутам из конфигурации.
     * <p>
     * Универсальный подход, сочетающий аннотационную и декларативную защиту.
     */
    PROTECT_ANNOTATED_OR_ROUTES
}
