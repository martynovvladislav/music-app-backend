package ru.martynov.music_service.application.result;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Result<T> {
    private final boolean success;
    private final T data;
    private final String error;

    private Result(T data, String error) {
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

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error);
    }

    public Optional<T> getDataOptional() {
        return Optional.ofNullable(data);
    }
}
