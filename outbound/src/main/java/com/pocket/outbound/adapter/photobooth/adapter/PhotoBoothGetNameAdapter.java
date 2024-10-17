package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.domain.port.photobooth.PhotoBoothGetNamePort;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;


@AdapterService
@RequiredArgsConstructor
public class PhotoBoothGetNameAdapter implements PhotoBoothGetNamePort {

    private final PhotoBoothRepository photoBoothRepository;

    @Override
    public String getPhotoBoothName(Long photoboothId) {

        JpaPhotoBooth photoBooth = photoBoothRepository.findById(photoboothId)
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        return photoBooth.getPhotoBooth().getName();
    }
}
