package com.martynov.user_service.like.domain.repository;

import com.martynov.user_service.like.domain.entity.Like;

import java.util.List;

public interface LikeRepository {

    boolean existsByUserIdAndTrackId(Long userId, Long trackId);

    void save(Like like);

    void deleteByUserIdAndTrackId(Long userId, Long trackId);

    List<Like> findAllByUserId(Long userId);
}
