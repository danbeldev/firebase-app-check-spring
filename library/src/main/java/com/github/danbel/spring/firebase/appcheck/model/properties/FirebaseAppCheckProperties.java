package com.github.danbel.spring.firebase.appcheck.model.properties;

import com.github.danbel.spring.firebase.appcheck.exception.FirebaseAppCheckErrorHandler;
import com.github.danbel.spring.firebase.appcheck.exception.impl.FirebaseAppCheckErrorHandlerImpl;
import com.github.danbel.spring.firebase.appcheck.core.services.FirebaseAppCheckTokenVerifierService;
import com.github.danbel.spring.firebase.appcheck.core.services.impl.FirebaseAppCheckTokenVerifierServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Конфигурация Firebase App Check для проверки подлинности приложений.
 * Поддерживает настройку как через application.yml, так и через Java-код.
 */
@ConfigurationProperties(prefix = "firebase.app-check")
public class FirebaseAppCheckProperties {

    /**
     * Идентификатор проекта Firebase.
     */
    private String projectId;

    /**
     * Номер проекта Firebase.
     */
    private String projectNumber;

    /**
     * Имя HTTP-заголовка, в котором ожидается токен App Check.
     */
    private String headerName = "X-Firebase-AppCheck";

    /**
     * Базовый URL API Firebase App Check.
     */
    private String apiBaseUrl = "https://firebaseappcheck.googleapis.com";

    /**
     * URL для получения открытых ключей App Check.
     * Если не задано, будет установлено значение по умолчанию на основе apiBaseUrl.
     */
    private String publicKeyUrl;

    /**
     * Включена ли проверка App Check.
     */
    private boolean enabled = true;

    /**
     * Стратегия безопасности
     */
    private FirebaseAppCheckSecureStrategy secureStrategy = FirebaseAppCheckSecureStrategy.PROTECT_ANNOTATED_OR_ROUTES;

    /**
     * Список защищённых маршрутов.
     */
    private List<FirebaseProtectedRoute> routes = new ArrayList<>();

    /**
     * Дополнительное условие безопасности, выполняемое после валидации токена.
     */
    private FirebaseAppCheckCondition additionalSecurityCheck;

    /**
     * Коллбэк, вызываемый после успешной проверки безопасности.
     */
    private FirebaseAppCheckCallback afterSecurityCheck;

    @PostConstruct
    public void init() {
        if (publicKeyUrl == null || publicKeyUrl.isEmpty()) {
            publicKeyUrl = apiBaseUrl + "/v1/jwks";
        }
    }

    public void addRoute(HttpMethod method, String path) {
        routes.add(new FirebaseProtectedRoute(method, path));
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public String getPublicKeyUrl() {
        return publicKeyUrl;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public FirebaseAppCheckSecureStrategy getSecureStrategy() {
        return secureStrategy;
    }

    public List<FirebaseProtectedRoute> getRoutes() {
        return routes;
    }

    public FirebaseAppCheckCondition getAdditionalSecurityCheck() {
        return additionalSecurityCheck;
    }

    public FirebaseAppCheckCallback getAfterSecurityCheck() {
        return afterSecurityCheck;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public void setPublicKeyUrl(String publicKeyUrl) {
        this.publicKeyUrl = publicKeyUrl;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setSecureStrategy(FirebaseAppCheckSecureStrategy secureStrategy) {
        this.secureStrategy = secureStrategy;
    }

    public void setRoutes(List<FirebaseProtectedRoute> routes) {
        this.routes = routes;
    }

    public void setAdditionalSecurityCheck(FirebaseAppCheckCondition additionalSecurityCheck) {
        this.additionalSecurityCheck = additionalSecurityCheck;
    }

    public void setAfterSecurityCheck(FirebaseAppCheckCallback afterSecurityCheck) {
        this.afterSecurityCheck = afterSecurityCheck;
    }
}
