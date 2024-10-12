package com.pocket.domain.entity.review;

import com.pocket.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Column(name = "rating")
    private int rating;

    @Column(name = "content")
    private String content;

    @ElementCollection(targetClass = BoothFeature.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "booth_features")
    private List<BoothFeature> boothFeatures;

    @ElementCollection(targetClass = PhotoFeature.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "photo_features")
    private List<PhotoFeature> photoFeatures;




}
