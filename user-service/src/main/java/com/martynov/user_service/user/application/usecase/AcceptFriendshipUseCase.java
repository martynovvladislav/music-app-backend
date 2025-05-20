package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.user.application.dto.friendship.AcceptFriendshipRequest;
import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.domain.model.Friendship;
import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.FriendshipRepository;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AcceptFriendshipUseCase {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Transactional
    public Result<Void> execute(final AcceptFriendshipRequest request, final boolean isAccepted) {
        final Optional<Friendship> friendshipOptional = friendshipRepository.findById(request.friendshipId);
        if (friendshipOptional.isEmpty()) {
            return Result.failure("Friendship not found", ErrorType.USER);
        }

        final Optional<User> acceptorOptional = userRepository.findById(request.acceptorId);
        if (acceptorOptional.isEmpty()) {
            return Result.failure("Acceptor user not found", ErrorType.USER);
        }

        try {
            final Friendship friendship = friendshipOptional.get();
            if (isAccepted) {
                friendship.accept(acceptorOptional.get());
                friendshipRepository.save(friendship);
            } else {
                friendshipRepository.delete(friendship);
            }

            return Result.success();
        } catch (Exception e) {
            return Result.failure(e.getMessage(), ErrorType.UNEXPECTED);
        }
    }
}
