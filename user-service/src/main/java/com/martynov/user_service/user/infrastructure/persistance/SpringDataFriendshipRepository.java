package com.martynov.user_service.user.infrastructure.persistance;

import com.martynov.user_service.user.domain.model.Friendship;
import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataFriendshipRepository implements FriendshipRepository {
    private final JpaFriendShipRepository jpaFriendShipRepository;

    @Override
    public List<Friendship> findUsersFriendships(Long userId) {
        return  jpaFriendShipRepository.findAllByUserId(userId);
    }

    @Override
    public void save(Friendship friendship) {
        jpaFriendShipRepository.save(friendship);
    }

    @Override
    public Optional<Friendship> findById(Long id) {
        return jpaFriendShipRepository.findById(id);
    }

    @Override
    public void delete(Friendship friendship) {
        jpaFriendShipRepository.delete(friendship);
    }

    @Override
    public boolean existsByUserIds(Long userId, Long friendId) {
        return jpaFriendShipRepository.existsByUserIds(userId, friendId);
    }
}
