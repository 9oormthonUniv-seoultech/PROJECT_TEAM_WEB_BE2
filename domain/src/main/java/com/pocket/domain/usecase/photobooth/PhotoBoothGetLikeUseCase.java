package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothLikeDto;

import java.util.List;

public interface PhotoBoothGetLikeUseCase {

    List<PhotoBoothLikeDto> getLikedPhotos(String userEmail);

}
