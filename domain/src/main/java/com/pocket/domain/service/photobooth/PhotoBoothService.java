package com.pocket.domain.service.photobooth;

import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.dto.photobooth.PhotoBoothSearchDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.photobooth.PhotoBoothFindPort;
import com.pocket.domain.port.photobooth.PhotoBoothGetNamePort;
import com.pocket.domain.port.photobooth.PhotoBoothGetRatingPort;
import com.pocket.domain.port.photobooth.PhotoBoothSearchPort;
import com.pocket.domain.usecase.photobooth.PhotoBoothFindUseCase;
import com.pocket.domain.usecase.photobooth.PhotoBoothGetNameUseCase;
import com.pocket.domain.usecase.photobooth.PhotoBoothGetRatingUseCase;
import com.pocket.domain.usecase.photobooth.PhotoBoothSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PhotoBoothService implements PhotoBoothFindUseCase, PhotoBoothGetNameUseCase, PhotoBoothGetRatingUseCase, PhotoBoothSearchUseCase {

    private final PhotoBoothFindPort photoBoothFindPort;
    private final PhotoBoothGetRatingPort photoBoothGetRatingPort;
    private final PhotoBoothGetNamePort photoBoothGetNamePort;
    private final PhotoBoothSearchPort photoBoothSearchPort;

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
}
