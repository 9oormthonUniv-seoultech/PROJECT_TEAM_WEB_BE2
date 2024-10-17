package com.pocket.outbound.entity.review;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REVIEWBOOTHFEATURE")
@Builder
@AllArgsConstructor
public class JpaReviewBoothFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_boothfeature_map_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private JpaReview jpaReview;

    @ManyToOne
    @JoinColumn(name = "boothfeature_id")
    private JpaBoothFeature jpaBoothFeature;

    @Builder(access = AccessLevel.PRIVATE)
    private JpaReviewBoothFeature(JpaReview jpaReview, JpaBoothFeature jpaBoothFeature) {
        this.jpaReview = jpaReview;
        this.jpaBoothFeature = jpaBoothFeature;
    }

    public static JpaReviewBoothFeature of(JpaReview jpaReview, JpaBoothFeature jpaBoothFeature) {
        return JpaReviewBoothFeature.builder()
                .jpaReview(jpaReview)
                .jpaBoothFeature(jpaBoothFeature)
                .build();
    }

}
