package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.user.application.dto.user.UserTokenResponse;
import com.martynov.user_service.user.application.dto.user.UserAuthRequest;
import com.martynov.user_service.user.application.service.UserTokenProvider;
import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserLoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTokenProvider userTokenProvider;

    public Result<UserTokenResponse> execute(final UserAuthRequest authDto) {
        final Optional<User> userOptional = userRepository.findByUsername(authDto.username);

        if (userOptional.isEmpty()) {
            return Result.failure("User not found", ErrorType.USER);
        }

        final User user = userOptional.get();
        if (!passwordEncoder.matches(authDto.password, user.getPassword())) {
            return Result.failure("Incorrect password", ErrorType.USER);
        }

        String token = userTokenProvider.generateToken(user);
        return Result.success(new UserTokenResponse(token));
    }
}
