package com.martynov.user_service.common.application.utils;

import lombok.Getter;

@Getter
public class Result<T> {
    private final boolean success;
    private final T data;
    private final ErrorDesc error;

    private Result(T data, ErrorDesc error) {
        this.success = error == null;
        this.data = data;
        this.error = error;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(null, null);
    }

    public static <T> Result<T> failure(final String errorMessage, final ErrorType errorType) {
        final ErrorDesc errorDesc = new ErrorDesc(errorMessage, errorType);
        return new Result<>(null, errorDesc);
    }
}