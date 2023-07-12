package com.emretaskin.innova.service.interfaces;

import com.emretaskin.innova.dto.request.LoginRequest;
import com.emretaskin.innova.dto.request.RegisterRequest;
import com.emretaskin.innova.dto.response.LoginResponse;
import com.emretaskin.innova.dto.response.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
}
