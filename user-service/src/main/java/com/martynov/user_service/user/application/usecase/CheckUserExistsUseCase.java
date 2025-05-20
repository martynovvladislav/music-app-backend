package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserExistsUseCase {
    private final UserRepository userRepository;

    public Result<Boolean> execute(final Long userId) {
        return Result.success(userRepository.existsById(userId));
    }
}
