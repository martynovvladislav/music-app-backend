package com.martynov.user_service.like.application.usecase;

import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.like.application.dto.LikeDto;
import com.martynov.user_service.like.application.dto.UserLikesResponse;
import com.martynov.user_service.like.application.service.UserService;
import com.martynov.user_service.like.domain.entity.Like;
import com.martynov.user_service.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserLikesUseCase {
    private final LikeRepository likeRepository;
    private final UserService userService;

    public Result<UserLikesResponse> execute(final Long userId) {
        if (!userService.checkIfUserExists(userId)) {
            return Result.failure("User not found", ErrorType.USER);
        }

        final List<Like> likes = likeRepository.findAllByUserId(userId);
        final UserLikesResponse response = new UserLikesResponse(
                likes.stream().map(like -> new LikeDto(like.getTrackId(), like.getCreationTime())).toList()
        );

        return Result.success(response);
    }
}
