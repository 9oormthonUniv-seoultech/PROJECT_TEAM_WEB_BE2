package com.pocket.domain.dto.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

public record NearPhotoBoothInfo(
        Long id,
        String name,
        PhotoBoothBrand brand,
        double x,
        double y
) {
}
