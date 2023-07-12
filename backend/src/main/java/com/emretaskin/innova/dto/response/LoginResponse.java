package com.emretaskin.innova.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String jwtToken;

    private Long userId;
}