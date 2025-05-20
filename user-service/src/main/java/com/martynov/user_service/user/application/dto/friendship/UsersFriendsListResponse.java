package com.martynov.user_service.user.application.dto.friendship;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsersFriendsListResponse {
    public List<UsersFriend> friends;
    public List<UsersFriend> requestors;
    public List<UsersFriend> receivers;
}
