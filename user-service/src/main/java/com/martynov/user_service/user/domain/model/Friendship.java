package com.martynov.user_service.user.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendship")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestor_user_id", nullable = false)
    private User requestor;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private User receiver;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt;

    public Long getRequestorId() {
        return requestor.getId();
    }

    public Long getReceiverId() {
        return receiver.getId();
    }

    public boolean isAccepted() {
        return status == FriendshipStatus.ACCEPTED;
    }

    public boolean isPending() {
        return status == FriendshipStatus.PENDING;
    }

    public static Friendship create(final User requestor, final User receiver) {
        return new Friendship(null, requestor, receiver, FriendshipStatus.PENDING, LocalDateTime.now(), null);
    }

    public void accept(final User acceptor) {
        if (status == FriendshipStatus.ACCEPTED) {
            return;
        }

        if (receiver.getId().equals(acceptor.getId())) {
            status = FriendshipStatus.ACCEPTED;
        } else {
            throw new IllegalArgumentException("User are not allowed to accept this friendship");
        }
    }
}

