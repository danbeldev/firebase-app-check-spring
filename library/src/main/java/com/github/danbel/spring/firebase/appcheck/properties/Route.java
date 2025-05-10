package com.github.danbel.spring.firebase.appcheck.properties;

import org.springframework.http.HttpMethod;

public record Route(
    HttpMethod method,
    String path
) { }
