package com.martynov.user_service.user.infrastructure.api.internal;

import com.martynov.user_service.like.application.dto.UserLikesResponse;
import com.martynov.user_service.like.infrastructure.internal.LikeApi;
import com.martynov.user_service.user.application.dto.music.LikeDto;
import com.martynov.user_service.user.application.service.LikeClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InternalLikeClient implements LikeClient {
    private final LikeApi likeApi;

    @Override
    public List<LikeDto> getLikesByUserId(Long userId) {
        final UserLikesResponse response = likeApi.getLikes(userId);

        return response.likedTracks.stream()
                .map(likeDto -> new LikeDto(likeDto.trackId, likeDto.likedAt))
                .toList();
    }
}
