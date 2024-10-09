package com.pocket.outbound.adapter.photobooth.mapper;

import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.outbound.entity.JpaPhotoBooth;
import org.springframework.stereotype.Component;

@Component
public class PhotoBoothOutBoundMapper {

    public NearPhotoBoothInfo toDTO(JpaPhotoBooth photoBooth) {
        return new NearPhotoBoothInfo(
                photoBooth.getId(),
                photoBooth.getPhotoBooth().getName(),
                photoBooth.getPhotoBooth().getPhotoBoothBrand(),
                photoBooth.getPhotoBooth().getX(),
                photoBooth.getPhotoBooth().getY()
        );
    }
}
