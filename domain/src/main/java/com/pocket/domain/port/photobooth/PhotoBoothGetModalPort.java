package com.pocket.domain.port.photobooth;

import com.pocket.domain.dto.photobooth.PhotoBoothModalDto;

public interface PhotoBoothGetModalPort {

    PhotoBoothModalDto getPhotoBoothModal(Long photoBoothId);

}
