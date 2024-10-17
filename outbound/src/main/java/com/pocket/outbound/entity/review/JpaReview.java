package com.pocket.outbound.entity.review;

import com.pocket.domain.entity.review.Review;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REVIEW")
@Builder
@AllArgsConstructor
public class JpaReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaUser jpaUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaPhotoBooth photoBooth;

    @Embedded
    private Review review;

}
