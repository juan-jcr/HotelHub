package com.jacr.presentation.controller;

import com.jacr.persistence.entities.UserEntity;
import com.jacr.presentation.dto.LoginRequest;
import com.jacr.presentation.dto.Response;
import com.jacr.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid UserEntity user){
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
