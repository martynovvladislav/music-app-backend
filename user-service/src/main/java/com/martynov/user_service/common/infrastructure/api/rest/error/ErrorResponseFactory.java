package com.martynov.user_service.common.infrastructure.api.rest.error;

import com.martynov.user_service.common.application.utils.ErrorDesc;
import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ErrorResponseFactory {
    public ResponseEntity<ApiErrorResponse> createFromResult(final Result<?> result) {
        final ErrorDesc errorDesc = result.getError();
        return ResponseEntity
                .status(getStatus(errorDesc.getType()))
                .body(ApiErrorResponse.create(errorDesc));
    }

    private int getStatus(final ErrorType errorType) {
        if (Objects.requireNonNull(errorType) == ErrorType.USER) {
            return 400;
        }
        return 500;
    }
}
