package com.martynov.user_service.user.application.dto.friendship;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.martynov.user_service.user.domain.model.Friendship;
import com.martynov.user_service.user.domain.model.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsersFriend {
    public Long friendshipId;
    public String username;
    public Long userId;
    public String imageUrl;

    public static UsersFriend create(final Pair<Friendship, User> pair) {
        final Long friendshipId = pair.getKey().getId();
        final Long userId = pair.getValue().getId();
        final String imageUrl = pair.getValue().getPhotoUrl();
        final String username = pair.getValue().getUsername();

        return new UsersFriend(friendshipId, username, userId, imageUrl);
    }
}
