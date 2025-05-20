package com.martynov.user_service.common.infrastructure.api.rest.error;

import com.martynov.user_service.common.application.utils.ErrorDesc;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiErrorResponse {
    public String error;
    public String type;

    public static ApiErrorResponse create(final ErrorDesc errorDesc) {
        return new ApiErrorResponse(errorDesc.getMessage(), errorDesc.getType().name());
    }
}
