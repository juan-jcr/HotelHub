package com.jacr.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HelloController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String hello(){
        return "Hello ADMIN";
    }

    @GetMapping("/user")
    public String hola(){
        return "Hola USER";
    }
}
