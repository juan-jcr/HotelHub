package com.jacr.services.auth;

import com.jacr.persistence.entities.UserEntity;
import com.jacr.presentation.dto.LoginRequest;
import com.jacr.presentation.dto.Response;


public interface AuthService {
    Response registerUser(UserEntity user);
    Response login(LoginRequest loginRequest);
}
