package com.pocket.outbound.entity;

import com.pocket.domain.entity.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REVIEW")
public class JpaReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaPhotoBooth photoBooth;

    @Embedded
    private Review review;

}
