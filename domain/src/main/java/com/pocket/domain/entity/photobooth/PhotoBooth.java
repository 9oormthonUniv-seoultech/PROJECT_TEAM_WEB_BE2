package com.pocket.domain.entity.photobooth;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoBooth {

    @Column(name = "name")
    private String name;

    @Column(name = "road")
    private String road;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "photo_booth_brand")
    private PhotoBoothBrand photoBoothBrand;

    // 총 리뷰 수
    private int totalReviews;

    // 평균 별점
    @Column(precision = 3, scale = 2)
    private BigDecimal averageRating;  // 기본값을 0으로 설정

    private PhotoBooth(String name, String road, Double x, Double y, PhotoBoothBrand photoBoothBrand) {
        this.name = name;
        this.road = road;
        this.x = x;
        this.y = y;
        this.photoBoothBrand = photoBoothBrand;
        this.totalReviews = 0;
        this.averageRating = BigDecimal.ZERO;
    }

    // 정적 팩토리 메서드
    public static PhotoBooth create(String name, String road, Double x, Double y, PhotoBoothBrand brand) {
        return new PhotoBooth(name, road, x, y, brand);
    }

    public void updateRating(int newRating) {
        this.totalReviews += 1;
        BigDecimal totalScore = this.averageRating.multiply(BigDecimal.valueOf(this.totalReviews - 1))
                .add(BigDecimal.valueOf(newRating));
        this.averageRating = totalScore.divide(BigDecimal.valueOf(this.totalReviews), 2, BigDecimal.ROUND_HALF_UP);
    }

}
