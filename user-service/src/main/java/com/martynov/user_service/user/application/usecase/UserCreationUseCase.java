package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.application.dto.user.UserRegisterRequest;
import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserCreationUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Result<Void> execute(final UserRegisterRequest registerDto) {
        try {
            if (userRepository.existsByUsername(registerDto.username)) {
                return Result.failure("Username has been already taken", ErrorType.USER);
            }

            final String encodedPassword = passwordEncoder.encode(registerDto.password);
            final User user = User.create(registerDto.username, encodedPassword);
            userRepository.save(user);

            return Result.success();
        } catch (Exception e) {
            return Result.failure(e.getMessage(), ErrorType.UNEXPECTED);
        }
    }
}
