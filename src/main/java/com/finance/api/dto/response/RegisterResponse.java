package com.finance.api.dto.response;

import lombok.Getter;

@Getter
public class RegisterResponse {
    private final String username;
    private final String token;

    public RegisterResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
