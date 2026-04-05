package com.finance.api.dto.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final T data;
    private final String errorMessage;
    private final int statusCode;

    public ApiResponse(T data, String errorMessage, int statusCode) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null, 200);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(data, null, 201);
    }

    public static <T> ApiResponse<T> error(int statusCode, String errorMessage) {
        return new ApiResponse<>(null, errorMessage, statusCode);
    }
}
