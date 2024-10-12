package com.pocket.outbound.adapter.photobooth.adpater;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.domain.port.photobooth.PhotoBoothGetRatingPort;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothGetRatingAdapter implements PhotoBoothGetRatingPort {

    private final PhotoBoothRepository photoBoothRepository;

    @Override
    public BigDecimal getRating(Long photoboothId) {
        JpaPhotoBooth photoBooth = photoBoothRepository.findById(photoboothId)
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        return photoBooth.getRating();
    }
}
