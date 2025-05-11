package com.github.danbel.spring.firebase.appcheck.controllers;

import com.github.danbel.spring.firebase.appcheck.core.annotations.RequireFirebaseAppCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @GetMapping("/unsecured")
    public String unsecured() {
        return "unsecured";
    }

    @RequireFirebaseAppCheck
    @GetMapping("/annotation")
    public String secured() {
        return "secured";
    }

    @PostMapping("/route")
    public String securedRoute() {
        return "route";
    }
}