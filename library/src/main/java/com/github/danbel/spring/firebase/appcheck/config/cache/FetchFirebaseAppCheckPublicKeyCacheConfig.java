package com.github.danbel.spring.firebase.appcheck.config.cache;

import java.time.Duration;
import java.util.Objects;

/**
 * Конфигурация кэширования публичных ключей Firebase App Check.
 * Определяет размер кэша и время жизни записей в кэше.
 */
public final class FetchFirebaseAppCheckPublicKeyCacheConfig {

    /**
     * Максимальное количество ключей, хранимых в кэше
     * */
    private Long cacheSize = 10L;

    /**
     * Продолжительность времени, в течение которого ключи остаются актуальными в кэше
     * */
    private Duration expiresIn = Duration.ofHours(24);

    public Long getCacheSize() {
        return cacheSize;
    }

    public Duration getExpiresIn() {
        return expiresIn;
    }

    public void setCacheSize(Long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void setExpiresIn(Duration expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FetchFirebaseAppCheckPublicKeyCacheConfig) obj;
        return Objects.equals(this.cacheSize, that.cacheSize) &&
                Objects.equals(this.expiresIn, that.expiresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cacheSize, expiresIn);
    }

    @Override
    public String toString() {
        return "FetchFirebaseAppCheckPublicKeyCacheConfig[" +
                "cacheSize=" + cacheSize + ", " +
                "expiresIn=" + expiresIn + ']';
    }
}
