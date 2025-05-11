package com.github.danbel.spring.firebase.appcheck.model.properties;

import org.springframework.http.HttpMethod;

/**
 * Маршрут, защищённый с помощью Firebase App Check.
 * <p>
 * Используется для явного указания HTTP-метода и пути, подлежащих проверке.
 * Эти маршруты сопоставляются с входящими запросами при выборе стратегии {@code PROTECT_ROUTES}
 * или {@code PROTECT_ANNOTATED_OR_ROUTES}.
 *
 * @param method HTTP-метод (GET, POST, PUT и т.д.)
 * @param path   путь запроса, например {@code /api/user/**} или {@code /admin/settings}
 */
public record FirebaseProtectedRoute(
        HttpMethod method,
        String path
) { }

