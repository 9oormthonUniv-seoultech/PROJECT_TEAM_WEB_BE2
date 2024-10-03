package com.pocket.domain.dto.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

public record PhotoBoothFindResponseDto(
        String name,
        String road,
        Double x,
        Double y,
        PhotoBoothBrand photoBoothBrand
) {
}

