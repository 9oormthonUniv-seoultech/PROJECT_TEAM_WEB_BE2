package com.pocket.domain.entity.photobooth;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private PhotoBooth(String name, String road, Double x, Double y, PhotoBoothBrand photoBoothBrand) {
        this.name = name;
        this.road = road;
        this.x = x;
        this.y = y;
        this.photoBoothBrand = photoBoothBrand;
    }

    // 정적 팩토리 메서드
    public static PhotoBooth create(String name, String road, Double x, Double y, PhotoBoothBrand brand) {
        return new PhotoBooth(name, road, x, y, brand);
    }
}
