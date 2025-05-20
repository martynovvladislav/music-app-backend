package com.martynov.user_service.user.infrastructure.api.internal;

import com.martynov.user_service.user.application.usecase.CheckUserExistsUseCase;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApi {
    private final CheckUserExistsUseCase userExistsUseCase;

    public boolean userExists(final Long userId) {
        return userExistsUseCase.execute(userId).getData();
    }
}
