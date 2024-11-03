package com.pocket.domain.usecase.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothModalDto;

public interface PhotoBoothGetModalUseCase {

    PhotoBoothModalDto getPhotoBoothModal(Long photoboothId);

}
