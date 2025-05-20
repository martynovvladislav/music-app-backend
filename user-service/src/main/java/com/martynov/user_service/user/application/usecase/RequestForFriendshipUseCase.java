package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.user.application.dto.friendship.FriendshipRequest;
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
public class RequestForFriendshipUseCase {
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Transactional
    public Result<Void> execute(final FriendshipRequest friendshipRequest) {
        if (friendshipRequest.userId.equals(friendshipRequest.friendId)) {
            return Result.failure("Cannot send request to yourself", ErrorType.USER);
        }

        final Optional<User> requestorOptional = userRepository.findById(friendshipRequest.userId);
        if (requestorOptional.isEmpty()) {
            return Result.failure("Requestor user not found", ErrorType.USER);
        }

        final Optional<User> receiverOptional = userRepository.findById(friendshipRequest.friendId);
        if (receiverOptional.isEmpty()) {
            return Result.failure("Receiver user not found", ErrorType.USER);
        }

        try {
            final User requestor = requestorOptional.get();
            final User receiver = receiverOptional.get();
            if (friendshipRepository.existsByUserIds(requestor.getId(), receiver.getId())) {
                return Result.failure("Friendship already exists", ErrorType.USER);
            }

            final Friendship friendship = Friendship.create(requestor, receiver);
            friendshipRepository.save(friendship);

            return Result.success();
        } catch (Exception e) {
            return Result.failure(e.getMessage(), ErrorType.UNEXPECTED);
        }
    }
}
