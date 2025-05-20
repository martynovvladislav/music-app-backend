package com.martynov.user_service.user.application.service;

import com.martynov.user_service.like.infrastructure.internal.LikeApi;
import com.martynov.user_service.user.application.dto.music.LikeDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeService {
    private final LikeClient likeClient;

    public List<LikeDto> getLikesByUserId(final Long userId) {
        return likeClient.getLikesByUserId(userId);
    }
}
