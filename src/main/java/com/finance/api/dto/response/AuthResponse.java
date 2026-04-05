package com.finance.api.dto.response;

import lombok.Getter;

@Getter
public class AuthResponse {
    private final String username;
    private final String token;

    public AuthResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
