package com.martynov.user_service.user.infrastructure.api.rest;

import com.martynov.user_service.user.application.dto.friendship.AcceptFriendshipRequest;
import com.martynov.user_service.user.application.dto.friendship.FriendshipRequest;
import com.martynov.user_service.user.application.dto.friendship.GetUsersFriendsRequest;
import com.martynov.user_service.user.application.dto.friendship.UsersFriendsListResponse;
import com.martynov.user_service.user.application.usecase.AcceptFriendshipUseCase;
import com.martynov.user_service.user.application.usecase.GetUsersFriendshipsUseCase;
import com.martynov.user_service.user.application.usecase.RequestForFriendshipUseCase;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.common.infrastructure.api.rest.error.ErrorResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final GetUsersFriendshipsUseCase getUsersFriendshipsUseCase;
    private final RequestForFriendshipUseCase requestForFriendshipUseCase;
    private final AcceptFriendshipUseCase acceptFriendshipUseCase;

    private final ErrorResponseFactory errorResponseFactory;

    @PostMapping
    public ResponseEntity<?> requestForFriendship(
            @RequestBody final FriendshipRequest friendshipRequest,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        friendshipRequest.userId = Long.parseLong(userDetails.getUsername());

        final Result<Void> result = requestForFriendshipUseCase.execute(friendshipRequest);

        if (result.isSuccess()) {
            return ResponseEntity.created(URI.create("")).build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @GetMapping
    public ResponseEntity<?> getFriendships(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        final Long authUserId = Long.parseLong(userDetails.getUsername());
        final GetUsersFriendsRequest request = new GetUsersFriendsRequest(authUserId);

        final Result<UsersFriendsListResponse> result = getUsersFriendshipsUseCase.execute(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result.getData());
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<?> acceptFriendship(
            @PathVariable("id") Long friendshipId,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        final Long authUserId = Long.parseLong(userDetails.getUsername());
        final AcceptFriendshipRequest acceptFriendshipRequest = new AcceptFriendshipRequest(friendshipId, authUserId);

        final Result<Void> result = acceptFriendshipUseCase.execute(
                acceptFriendshipRequest,
                true
        );

        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<?> rejectFriendship(
            @PathVariable("id") Long friendshipId,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        final Long authUserId = Long.parseLong(userDetails.getUsername());
        final AcceptFriendshipRequest acceptFriendshipRequest = new AcceptFriendshipRequest(friendshipId, authUserId);

        final Result<Void> result = acceptFriendshipUseCase.execute(
                acceptFriendshipRequest,
                false
        );

        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return errorResponseFactory.createFromResult(result);
        }
    }
}
