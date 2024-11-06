package com.pocket.domain.service.photobooth;

import com.pocket.domain.dto.photobooth.*;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.photobooth.*;
import com.pocket.domain.usecase.photobooth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PhotoBoothService implements PhotoBoothFindUseCase, PhotoBoothGetNameUseCase, PhotoBoothGetRatingUseCase, PhotoBoothSearchUseCase, PhotoBoothGetModalUseCase, PhotoBoothVisitedUseCase {

    private final PhotoBoothFindPort photoBoothFindPort;
    private final PhotoBoothGetRatingPort photoBoothGetRatingPort;
    private final PhotoBoothGetNamePort photoBoothGetNamePort;
    private final PhotoBoothSearchPort photoBoothSearchPort;
    private final PhotoBoothGetModalPort photoBoothGetModalPort;
    private final PhotoBoothVisitedPort photoBoothVisitedPort;

    public PhotoBoothFindResponseDto findPhotoBoothResponse(Long id) {
        return photoBoothFindPort.findById(id);
    }

    public String getPhotoBoothName(Long photoboothId) {
        return photoBoothGetNamePort.getPhotoBoothName(photoboothId);
    }
    public List<NearPhotoBoothInfo> findNearPhotoBooth(double lat, double lon, List<PhotoBoothBrand> brand) {
        return photoBoothFindPort.getPhotoboothWithin2Km(lat, lon, brand);
    }

    public BigDecimal getPhotoBoothRating(Long photoboothId) {
        return photoBoothGetRatingPort.getRating(photoboothId);
    }

    public List<PhotoBoothSearchDto> searchPhotoBooth(String keyword) {
        return photoBoothSearchPort.searchPhotoBooth(keyword);
    }

    @Override
    public PhotoBoothModalDto getPhotoBoothModal(Long photoboothId) {
        return photoBoothGetModalPort.getPhotoBoothModal(photoboothId);
    }

    @Override
    public List<PhotoBoothVisitedDto> getVisitedPhotoBooths(String userEmail) {
        return photoBoothVisitedPort.getVisitedPhotoBooths(userEmail);
    }
}
