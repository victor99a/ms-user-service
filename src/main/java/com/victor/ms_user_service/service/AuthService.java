package com.victor.ms_user_service.service;

import com.victor.ms_user_service.dto.AuthResponse;
import com.victor.ms_user_service.dto.LoginRequest;
import com.victor.ms_user_service.dto.RegistroRequest;

public interface AuthService {

    AuthResponse registrar(RegistroRequest request);

    AuthResponse login(LoginRequest request);
}