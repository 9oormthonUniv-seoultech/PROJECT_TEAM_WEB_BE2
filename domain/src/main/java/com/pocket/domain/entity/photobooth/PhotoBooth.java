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

    @Column(name = "location")
    private String location;

    @Column(name = "photo_booth_brand")
    private PhotoBoothBrand photoBoothBrand;
}
