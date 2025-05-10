package com.github.danbel.spring.firebase.appcheck;

public record FetchFirebaseAppCheckPublicKeyConfig(
        FetchFirebaseAppCheckPublicKeyCacheConfig cacheConfiguration,
        FetchFirebaseAppCheckPublicKeyRateLimitedConfig rateLimitedConfig
) {}
