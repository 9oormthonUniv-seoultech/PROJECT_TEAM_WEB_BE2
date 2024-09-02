package com.pocket.outbound.entity;

import com.pocket.domain.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<JpaReviewImage> images = new ArrayList<>();

}
