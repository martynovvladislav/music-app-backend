package com.martynov.user_service.like.infrastructure.rest.api;

import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.common.infrastructure.api.rest.error.ErrorResponseFactory;
import com.martynov.user_service.like.application.dto.LikeTrackRequest;
import com.martynov.user_service.like.application.dto.UserLikesResponse;
import com.martynov.user_service.like.application.usecase.GetUserLikesUseCase;
import com.martynov.user_service.like.application.usecase.LikeTrackUseCase;
import com.martynov.user_service.like.application.usecase.UnlikeTrackUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeTrackUseCase likeTrackUseCase;
    private final UnlikeTrackUseCase unlikeTrackUseCase;
    private final GetUserLikesUseCase getUserLikesUseCase;

    private final ErrorResponseFactory errorResponseFactory;

    @PostMapping("/track")
    public ResponseEntity<?> likeTrack(
            @RequestBody final LikeTrackRequest likeTrackRequest,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        likeTrackRequest.userId = Long.parseLong(userDetails.getUsername());

        final Result<Void> result = likeTrackUseCase.execute(likeTrackRequest);

        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @DeleteMapping("/track")
    public ResponseEntity<?> unlikeTrack(
            @RequestBody final LikeTrackRequest likeTrackRequest,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        likeTrackRequest.userId = Long.parseLong(userDetails.getUsername());

        final Result<Void> result = unlikeTrackUseCase.execute(likeTrackRequest);

        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @GetMapping("/track")
    public ResponseEntity<?> getLikedTracks(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        final Long userId = Long.parseLong(userDetails.getUsername());

        final Result<UserLikesResponse> result = getUserLikesUseCase.execute(userId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result.getData());
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }
}
