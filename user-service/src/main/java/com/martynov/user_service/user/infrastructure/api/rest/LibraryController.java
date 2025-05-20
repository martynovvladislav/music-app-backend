package com.martynov.user_service.user.infrastructure.api.rest;

import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.common.infrastructure.api.rest.error.ErrorResponseFactory;
import com.martynov.user_service.user.application.dto.music.LibraryDto;
import com.martynov.user_service.user.application.dto.music.UserLibraryRequest;
import com.martynov.user_service.user.application.usecase.GetLibraryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final GetLibraryUseCase getLibraryUseCase;

    private final ErrorResponseFactory errorResponseFactory;

    @GetMapping
    public ResponseEntity<?> getUserProfile(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        final Long userId = Long.parseLong(userDetails.getUsername());
        final UserLibraryRequest request = new UserLibraryRequest(userId);

        final Result<LibraryDto> result = getLibraryUseCase.execute(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }
}
