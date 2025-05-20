package com.martynov.user_service.user.application.service;

import com.martynov.user_service.user.application.dto.music.LikeDto;

import java.util.List;

public interface LikeClient {
    List<LikeDto> getLikesByUserId(final Long userId);
}
