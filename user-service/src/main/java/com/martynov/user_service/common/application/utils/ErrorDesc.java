package com.martynov.user_service.common.application.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDesc {
    private String message;
    private ErrorType type;
}
