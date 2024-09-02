package com.pocket.outbound.entity;

import com.pocket.domain.entity.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.pocket.domain.entity.image.ImageType.REVIEW;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REVIEWIMAGE")
public class JpaReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewImage_id")
    private Long id;

    @Embedded
    private Image image = new Image(REVIEW);

    @ManyToOne(fetch = FetchType.LAZY)
    private JpaReview review;

}

