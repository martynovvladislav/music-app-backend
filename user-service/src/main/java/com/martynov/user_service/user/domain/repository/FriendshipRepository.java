package com.martynov.user_service.user.domain.repository;

import com.martynov.user_service.user.domain.model.Friendship;
import com.martynov.user_service.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository {
    List<Friendship> findUsersFriendships(Long userId);

    void save(Friendship friendship);

    Optional<Friendship> findById(Long id);

    void delete(Friendship friendship);

    boolean existsByUserIds(Long userId, Long friendId);
}
