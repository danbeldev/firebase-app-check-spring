package com.github.danbel.spring.firebase.appcheck.config.cache;

import java.util.Objects;

/**
 * Класс конфигурации ограничения частоты запросов на получение ключей.
 */
public final class FetchFirebaseAppCheckPublicKeyRateLimitedConfig {

    /**
     * Флаг активации ограничения частоты запросов
     * */
    private Boolean enabled = true;

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FetchFirebaseAppCheckPublicKeyRateLimitedConfig) obj;
        return Objects.equals(this.enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled);
    }

    @Override
    public String toString() {
        return "FetchFirebaseAppCheckPublicKeyRateLimitedConfig[" +
                "enabled=" + enabled + ']';
    }
}