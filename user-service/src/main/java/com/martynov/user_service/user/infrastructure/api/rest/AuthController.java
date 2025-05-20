package com.martynov.user_service.user.infrastructure.api.rest;

import com.martynov.user_service.user.application.dto.user.UserTokenResponse;
import com.martynov.user_service.user.application.usecase.UserLoginUseCase;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.application.dto.user.UserAuthRequest;
import com.martynov.user_service.user.application.usecase.UserCreationUseCase;
import com.martynov.user_service.user.application.dto.user.UserRegisterRequest;
import com.martynov.user_service.common.infrastructure.api.rest.error.ErrorResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserCreationUseCase userCreationUseCase;
    private final UserLoginUseCase userLoginUseCase;
    private final ErrorResponseFactory errorResponseFactory;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody final UserRegisterRequest registerDto) {
        final Result<Void> result = userCreationUseCase.execute(registerDto);

        if (result.isSuccess()) {
            return ResponseEntity.created(URI.create("")).build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final UserAuthRequest userAuthRequest) {
        final Result<UserTokenResponse> result = userLoginUseCase.execute(userAuthRequest);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }
}
