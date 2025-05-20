package com.martynov.user_service.user.application.dto.music;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LikeDto {
    public Long trackId;
    public LocalDateTime likedAt;
}

