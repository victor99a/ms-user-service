package com.victor.ms_user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestProtectedController {

    @GetMapping("/api/protected/hello")
    public String hello() {
        return "Hola, estás autenticado";
    }
}