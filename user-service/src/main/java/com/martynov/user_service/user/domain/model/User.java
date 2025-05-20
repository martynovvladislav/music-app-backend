package com.martynov.user_service.user.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "friendship_code")
    private String friendshipCode;

    @Column(name = "creation_time")
    @CreationTimestamp
    private LocalDateTime creationTime;

    public static User create(final String username, final String password) {
        return new User(null, username, password, null, createFriendshipCode(), null);
    }

    private static String createFriendshipCode() {
        return RandomStringUtils.insecure().nextAscii(10);
    }
}
