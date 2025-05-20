package com.martynov.user_service.like.application.dto;

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
