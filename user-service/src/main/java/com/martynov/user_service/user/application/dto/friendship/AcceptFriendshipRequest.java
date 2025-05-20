package com.martynov.user_service.user.application.dto.friendship;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AcceptFriendshipRequest {
    public Long friendshipId;
    public Long acceptorId;
}
