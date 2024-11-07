package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothLikeDto;

import java.util.List;

public interface PhotoBoothGetLikePort {

    List<PhotoBoothLikeDto> getLikedPhotos(String userEmail);

}
