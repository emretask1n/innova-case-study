package com.emretaskin.innova.service.impl;

import com.emretaskin.innova.dto.request.LoginRequest;
import com.emretaskin.innova.dto.request.RegisterRequest;
import com.emretaskin.innova.dto.response.LoginResponse;
import com.emretaskin.innova.dto.response.RegisterResponse;
import com.emretaskin.innova.entity.User;
import com.emretaskin.innova.enums.UserRole;
import com.emretaskin.innova.service.interfaces.AuthenticationService;
import com.emretaskin.innova.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(UserRole.USER) // Varsayılan olarak USER rolü atandı, ihtiyaca göre değiştirilebilir
                .build();
        userService.saveUser(user);

        return RegisterResponse.builder()
                .message("User registered successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        User user = userService.loadUserByUsername(loginRequest.getUsername());

        String jwtToken = jwtService.generateToken(user);

        Long userId = user.getId();

        return LoginResponse.builder()
                .jwtToken(jwtToken)
                .userId(userId)
                .build();
    }
}
