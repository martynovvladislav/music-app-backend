package com.martynov.user_service.like.application.service;

import com.martynov.user_service.user.infrastructure.api.internal.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    public boolean checkIfUserExists(final Long userId) {
        return userClient.checkIfUserExists(userId);
    }
}
