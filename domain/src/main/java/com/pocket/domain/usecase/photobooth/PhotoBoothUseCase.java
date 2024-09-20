package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothResponseDto;

public interface PhotoBoothUseCase {

    PhotoBoothResponseDto getFindPhotoBoothResponse(Long id);

}
