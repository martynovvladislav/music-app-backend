package com.martynov.user_service.like.infrastructure.persistance;

import com.martynov.user_service.like.domain.entity.Like;
import com.martynov.user_service.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringDataLikeRepository implements LikeRepository {
    private final JpaLikeRepository jpaLikeRepository;
    @Override
    public boolean existsByUserIdAndTrackId(Long userId, Long trackId) {
        return jpaLikeRepository.existsByUserIdAndTrackId(userId, trackId);
    }

    @Override
    public void save(Like like) {
        jpaLikeRepository.save(like);
    }

    @Override
    public void deleteByUserIdAndTrackId(Long userId, Long trackId) {
        jpaLikeRepository.deleteByUserIdAndTrackId(userId, trackId);
    }

    @Override
    public List<Like> findAllByUserId(Long userId) {
        return jpaLikeRepository.findAllByUserId(userId);
    }
}
