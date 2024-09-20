package com.pocket.inbounds.photobooth.response;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

public record PhotoBoothResponse(
        String name,
        String location,
        PhotoBoothBrand photoBoothBrand
) {
}