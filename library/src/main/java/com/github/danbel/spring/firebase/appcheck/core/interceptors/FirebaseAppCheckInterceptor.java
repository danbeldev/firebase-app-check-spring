package com.github.danbel.spring.firebase.appcheck.core.interceptors;

import com.github.danbel.spring.firebase.appcheck.core.annotations.RequireFirebaseAppCheck;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;
import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckAdditionalSecurityCheckException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckException;
import com.github.danbel.spring.firebase.appcheck.exception.model.FirebaseAppCheckTokenMissingException;
import com.github.danbel.spring.firebase.appcheck.model.properties.FirebaseAppCheckProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.PathContainer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * Интерцептор для проверки Firebase App Check токенов.
 * В зависимости от стратегии безопасности, токены могут проверяться
 * для всех маршрутов, аннотированных методов или заданных путей.
 */
public class FirebaseAppCheckInterceptor implements HandlerInterceptor {

    private final FirebaseAppCheckProperties config;
    public final FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler;
    private final FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService;

    public FirebaseAppCheckInterceptor(
            FirebaseAppCheckProperties config,
            FirebaseAppCheckErrorHandler firebaseAppCheckErrorHandler,
            FirebaseAppCheckTokenVerifierService firebaseAppCheckTokenVerifierService
    ) {
        this.config = config;
        this.firebaseAppCheckErrorHandler = firebaseAppCheckErrorHandler;
        this.firebaseAppCheckTokenVerifierService = firebaseAppCheckTokenVerifierService;
    }

    /**
     * Вызывается перед обработкой запроса.
     * Выполняет проверку Firebase App Check токена, если это требуется согласно настройкам.
     *
     * @param request  HTTP-запрос
     * @param response HTTP-ответ
     * @param handler  обработчик маршрута
     * @return true, если запрос может быть продолжен; false — если произошла ошибка проверки
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!shouldValidateAppCheck(handler, request)) {
            return true;
        }

        String token = request.getHeader(config.getHeaderName());
        if (token == null) {
            return firebaseAppCheckErrorHandler
                    .handle(new FirebaseAppCheckTokenMissingException(), request, response, handler);
        }

        try {
            var jwt = firebaseAppCheckTokenVerifierService.verifyFirebaseAppCheckToken(
                    token,
                    config.getProjectId(),
                    config.getProjectNumber(),
                    config.getApiBaseUrl(),
                    config.getPublicKeyUrl()
            );

            if (config.getAdditionalSecurityCheck() != null &&
                    !config.getAdditionalSecurityCheck().evaluate(jwt)) {
                return firebaseAppCheckErrorHandler
                        .handle(new FirebaseAppCheckAdditionalSecurityCheckException(), request, response, handler);
            }

            if (config.getAfterSecurityCheck() != null) {
                config.getAfterSecurityCheck().execute(jwt);
            }

        } catch (FirebaseAppCheckException e) {
            return firebaseAppCheckErrorHandler.handle(e, request, response, handler);
        }

        return true;
    }

    /**
     * Определяет, нужно ли выполнять проверку App Check для данного обработчика и запроса.
     *
     * @param handler обработчик
     * @param request HTTP-запрос
     * @return true, если требуется проверка; false — если нет
     */
    private boolean shouldValidateAppCheck(Object handler, HttpServletRequest request) {
        if (!config.isEnabled()) {
            return false;
        }

        var strategy = config.getSecureStrategy();

        boolean isAnnotated = handler instanceof HandlerMethod method &&
                method.getMethod().getAnnotation(RequireFirebaseAppCheck.class) != null;

        boolean isRouteMatched = matchesProtectedRoutes(request);

        return switch (strategy) {
            case PROTECT_ALL -> true;
            case PROTECT_ANNOTATED -> isAnnotated;
            case PROTECT_ROUTES -> isRouteMatched;
            case PROTECT_ANNOTATED_OR_ROUTES -> isAnnotated || isRouteMatched;
        };
    }

    /**
     * Проверяет, соответствует ли запрос защищённым маршрутам.
     *
     * @param request HTTP-запрос
     * @return true, если запрос соответствует одному из защищённых маршрутов
     */
    private boolean matchesProtectedRoutes(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        return config.getRoutes().stream()
                .anyMatch(route ->
                        route.method().name().equalsIgnoreCase(method) &&
                                pathMatches(route.path(), path)
                );
    }

    /**
     * Проверяет, соответствует ли путь шаблону.
     *
     * @param pattern шаблон пути (например, "/api/**")
     * @param path    путь запроса (например, "/api/data/123")
     * @return true, если путь соответствует шаблону
     */
    private boolean pathMatches(String pattern, String path) {
        PathPatternParser parser = new PathPatternParser();
        PathPattern parsedPattern = parser.parse(pattern);
        PathContainer pathContainer = PathContainer.parsePath(path);
        return parsedPattern.matches(pathContainer);
    }
}
