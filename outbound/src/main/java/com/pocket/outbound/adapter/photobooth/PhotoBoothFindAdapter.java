package com.pocket.outbound.adapter.photobooth;

import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.port.photobooth.PhotoBoothFindPort;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoBoothFindAdapter implements PhotoBoothFindPort {

    private final PhotoBoothRepository photoBoothRepository;

    public PhotoBoothFindResponseDto findById(Long id) {
        JpaPhotoBooth entity = photoBoothRepository.findById(id)
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        return new PhotoBoothFindResponseDto(
                entity.getPhotoBooth().getName(),
                entity.getPhotoBooth().getRoad(),
                entity.getPhotoBooth().getX(),
                entity.getPhotoBooth().getY(),
                entity.getPhotoBooth().getPhotoBoothBrand()
        );
    }

}
