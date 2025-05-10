package com.github.danbel.spring.firebase.appcheck;

import java.time.Duration;

public record FetchFirebaseAppCheckPublicKeyCacheConfig(
        Long cacheSize,
        Duration expiresIn
) {}
