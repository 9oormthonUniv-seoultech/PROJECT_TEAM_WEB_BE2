package com.pocket.outbound.entity.photobooth;

import com.pocket.outbound.entity.JpaUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "LIKES")
public class JpaLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaPhotoBooth photoBooth;

    public static JpaLike createLike(JpaUser user, JpaPhotoBooth photoBooth) {
        return JpaLike.builder()
                .user(user)
                .photoBooth(photoBooth)
                .build();
    }
}
