package com.martynov.user_service.like.application.usecase;

import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.like.application.dto.LikeTrackRequest;
import com.martynov.user_service.like.application.service.UserService;
import com.martynov.user_service.like.domain.entity.Like;
import com.martynov.user_service.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LikeTrackUseCase {
    private final LikeRepository likeRepository;
    private final UserService userService;

    @Transactional
    public Result<Void> execute(final LikeTrackRequest request) {
        if (!userService.checkIfUserExists(request.userId)) {
            return Result.failure("User not found", ErrorType.USER);
        }

        //TODO check if track exists

        if (likeRepository.existsByUserIdAndTrackId(request.userId, request.trackId)) {
            return Result.success();
        }

        final Like like = Like.create(request.userId, request.trackId);
        likeRepository.save(like);

        return Result.success();
    }
}
