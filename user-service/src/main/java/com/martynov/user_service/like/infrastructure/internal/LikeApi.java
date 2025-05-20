package com.martynov.user_service.like.infrastructure.internal;

import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.like.application.dto.UserLikesResponse;
import com.martynov.user_service.like.application.usecase.GetUserLikesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeApi {
    private final GetUserLikesUseCase getUserLikesUseCase;

    public UserLikesResponse getLikes(final Long userId) {
        final Result<UserLikesResponse> userLikesResponseResult = getUserLikesUseCase.execute(userId);

        if (userLikesResponseResult.isSuccess()) {
            return userLikesResponseResult.getData();
        } else {
            throw new RuntimeException("Unexpected error");
        }
    }
}
