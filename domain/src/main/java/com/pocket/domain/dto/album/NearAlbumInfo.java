package com.pocket.domain.dto.album;

import com.pocket.domain.entity.photobooth.PhotoBoothBrand;

public record NearAlbumInfo(
        String photoUrl,
        double x,
        double y
) {
}
