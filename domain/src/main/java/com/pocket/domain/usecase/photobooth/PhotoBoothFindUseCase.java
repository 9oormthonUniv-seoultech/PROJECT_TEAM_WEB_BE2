package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;

public interface PhotoBoothFindUseCase {

    PhotoBoothFindResponseDto getPhotoBoothFindResponse(Long id);

}
