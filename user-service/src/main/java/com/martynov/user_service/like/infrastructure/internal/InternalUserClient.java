package com.martynov.user_service.like.infrastructure.internal;

import com.martynov.user_service.like.application.service.UserClient;
import com.martynov.user_service.user.infrastructure.api.internal.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalUserClient implements UserClient {
    private final UserApi userApi;
    @Override
    public boolean checkIfUserExists(Long userId) {
        return userApi.userExists(userId);
    }
}
