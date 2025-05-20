package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.user.application.dto.friendship.GetUsersFriendsRequest;
import com.martynov.user_service.user.application.dto.friendship.UsersFriend;
import com.martynov.user_service.user.application.dto.friendship.UsersFriendsListResponse;
import com.martynov.user_service.common.application.utils.ErrorType;
import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.domain.model.Friendship;
import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.FriendshipRepository;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class GetUsersFriendshipsUseCase {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public Result<UsersFriendsListResponse> execute(final GetUsersFriendsRequest request) {
        if (!userRepository.existsById(request.userId)) {
            return Result.failure("User not found", ErrorType.USER);
        }

        final List<Friendship> friendshipList = friendshipRepository.findUsersFriendships(request.userId);

        final List<Pair<Friendship, User>> friends = friendshipList.stream()
                .filter(Friendship::isAccepted)
                .map(friendship -> retrieveOtherUser().apply(friendship, request.userId))
                .toList();

        final List<Pair<Friendship, User>> receivers = friendshipList.stream()
                .filter(Friendship::isPending)
                .filter(friendship -> friendship.getRequestorId().equals(request.userId))
                .map(friendship -> retrieveOtherUser().apply(friendship, request.userId))
                .toList();

        final List<Pair<Friendship, User>> requestors = friendshipList.stream()
                .filter(Friendship::isPending)
                .filter(friendship -> friendship.getReceiverId().equals(request.userId))
                .map(friendship -> retrieveOtherUser().apply(friendship, request.userId))
                .toList();

        return Result.success(mapFriendsToDto(friends, requestors, receivers));
    }

    private UsersFriendsListResponse mapFriendsToDto(final List<Pair<Friendship, User>> friends, final List<Pair<Friendship, User>> requestors, List<Pair<Friendship, User>> receivers) {
        final List<UsersFriend> friendsDtos = friends.stream()
                .map(UsersFriend::create)
                .toList();

        final List<UsersFriend> requestorsDto = requestors.stream()
                .map(UsersFriend::create)
                .toList();

        final List<UsersFriend> receiversDto = receivers.stream()
                .map(UsersFriend::create)
                .toList();

        return new UsersFriendsListResponse(
                friendsDtos,
                requestorsDto,
                receiversDto
        );
    }

    private BiFunction<Friendship, Long, Pair<Friendship, User>> retrieveOtherUser() {
        return (friendship, id) -> {
            final User requestor = friendship.getRequestor();
            final User receiver = friendship.getReceiver();

            if (requestor.getId().equals(id)) {
                return Pair.of(friendship, receiver);
            } else {
                return Pair.of(friendship, requestor);
            }
        };
    }
}
