package com.pocket.domain.dto.photobooth;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

public record PhotoBoothResponseDto(
    String name,
    String location,
    PhotoBoothBrand photoBoothBrand
) {
}

