package com.martynov.user_service.user.infrastructure.persistance;

import com.martynov.user_service.user.domain.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaFriendShipRepository extends JpaRepository<Friendship, Long> {

    @Query(
            nativeQuery = true,
            value = """
                select * from friendship
                where (
                    requestor_user_id = :userId
                    or receiver_user_id = :userId
                )
            """)
    List<Friendship> findAllByUserId(Long userId);

    @Query(
            nativeQuery = true,
            value = """
                select exists (select 1 from friendship
                    where (
                        requestor_user_id = :userId
                        and receiver_user_id = :friendId
                    ) or (
                        requestor_user_id = :friendId
                        and receiver_user_id = :userId
                    )
                )
            """)
    boolean existsByUserIds(Long userId, Long friendId);
}
