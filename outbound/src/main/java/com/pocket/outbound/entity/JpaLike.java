package com.pocket.outbound.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "LIKES")
public class JpaLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaUser User;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaPhotoBooth photoBooth;

}
