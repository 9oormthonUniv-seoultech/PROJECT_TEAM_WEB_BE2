package com.pocket.domain.entity.review;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class Review {
    private int rating;
    private String content;

    // 리뷰 요약 - 부스 : 깔끔한 소품, 예쁜 셀카존 등등
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = BoothFeature.class)
    private List<BoothFeature> boothFeatures;

    // 리뷰 요약 - 사진 : 선명한 화질, 자연스러운 보정 등등
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PhotoFeature.class)
    private List<PhotoFeature> photoFeatures;

}
