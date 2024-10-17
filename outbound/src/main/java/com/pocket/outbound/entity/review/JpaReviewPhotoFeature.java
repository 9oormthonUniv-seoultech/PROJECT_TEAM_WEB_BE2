package com.pocket.outbound.entity.review;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REVIEWPHOTOFEATURE")
@Builder
@AllArgsConstructor
public class JpaReviewPhotoFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_photofeature_map_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private JpaReview jpaReview;

    @ManyToOne
    @JoinColumn(name = "photofeature_id")
    private JpaPhotoFeature jpaPhotoFeature;

    @Builder(access = AccessLevel.PRIVATE)
    private JpaReviewPhotoFeature(JpaReview jpaReview, JpaPhotoFeature jpaPhotoFeature) {
        this.jpaReview = jpaReview;
        this.jpaPhotoFeature = jpaPhotoFeature;
    }

    public static JpaReviewPhotoFeature of(JpaReview jpaReview, JpaPhotoFeature jpaPhotoFeature) {
        return JpaReviewPhotoFeature.builder()
                .jpaReview(jpaReview)
                .jpaPhotoFeature(jpaPhotoFeature)
                .build();
    }


}
