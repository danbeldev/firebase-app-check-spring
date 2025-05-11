package com.github.danbel.spring.firebase.appcheck.config.security;

import com.github.danbel.spring.firebase.appcheck.config.cache.FetchFirebaseAppCheckPublicKeyCacheConfig;
import com.github.danbel.spring.firebase.appcheck.config.cache.FetchFirebaseAppCheckPublicKeyRateLimitedConfig;

import java.util.Objects;

/**
 * Класс конфигурации для получения публичных ключей Firebase App Check.
 * Инкапсулирует различные настройки, включая кэширование и ограничение запросов.
 */
public final class FetchFirebaseAppCheckPublicKeyConfig {

    /**
     * Конфигурация кэширования публичных ключей
     * */
    private final FetchFirebaseAppCheckPublicKeyCacheConfig cacheConfiguration;

    /**
     * Конфигурация ограничения частоты запросов
     * */
    private final FetchFirebaseAppCheckPublicKeyRateLimitedConfig rateLimitedConfig;

    public FetchFirebaseAppCheckPublicKeyConfig() {
        cacheConfiguration = new FetchFirebaseAppCheckPublicKeyCacheConfig();
        rateLimitedConfig = new FetchFirebaseAppCheckPublicKeyRateLimitedConfig();
    }

    public FetchFirebaseAppCheckPublicKeyConfig(
            FetchFirebaseAppCheckPublicKeyCacheConfig cacheConfiguration,
            FetchFirebaseAppCheckPublicKeyRateLimitedConfig rateLimitedConfig
    ) {
        this.cacheConfiguration = cacheConfiguration;
        this.rateLimitedConfig = rateLimitedConfig;
    }

    public FetchFirebaseAppCheckPublicKeyCacheConfig cacheConfiguration() {
        return cacheConfiguration;
    }

    public FetchFirebaseAppCheckPublicKeyRateLimitedConfig rateLimitedConfig() {
        return rateLimitedConfig;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FetchFirebaseAppCheckPublicKeyConfig) obj;
        return Objects.equals(this.cacheConfiguration, that.cacheConfiguration) &&
                Objects.equals(this.rateLimitedConfig, that.rateLimitedConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cacheConfiguration, rateLimitedConfig);
    }

    @Override
    public String toString() {
        return "FetchFirebaseAppCheckPublicKeyConfig[" +
                "cacheConfiguration=" + cacheConfiguration + ", " +
                "rateLimitedConfig=" + rateLimitedConfig + ']';
    }
}
