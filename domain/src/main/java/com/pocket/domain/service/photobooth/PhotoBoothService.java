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
public class PhotoBoothService implements PhotoBoothFindUseCase, PhotoBoothGetNameUseCase, PhotoBoothGetRatingUseCase, PhotoBoothSearchUseCase, PhotoBoothGetModalUseCase, PhotoBoothVisitedUseCase, PhotoBoothLikeUseCase, PhotoBoothGetLikeUseCase, PhotoBoothCheckLikeUseCase, PhotoBoothDeleteLikeUseCase
{

    private final PhotoBoothFindPort photoBoothFindPort;
    private final PhotoBoothGetRatingPort photoBoothGetRatingPort;
    private final PhotoBoothGetNamePort photoBoothGetNamePort;
    private final PhotoBoothSearchPort photoBoothSearchPort;
    private final PhotoBoothGetModalPort photoBoothGetModalPort;
    private final PhotoBoothVisitedPort photoBoothVisitedPort;
    private final PhotoBoothLikePort photoBoothLikePort;
    private final PhotoBoothGetLikePort photoBoothGetLikePort;
    private final PhotoBoothCheckLikePort photoBoothCheckLikePort;
    private final PhotoBoothDeleteLikePort photoBoothDeleteLikePort;

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

    @Override
    public void photoBoothLike(Long photoboothId, String userEmail) {
        photoBoothLikePort.photoBoothLike(photoboothId, userEmail);
    }

    @Override
    public List<PhotoBoothLikeDto> getLikedPhotos(String userEmail) {
        return photoBoothGetLikePort.getLikedPhotos(userEmail);
    }

    @Override
    public Boolean checkLike(Long photoBoothId, String userEmail) {
        return photoBoothCheckLikePort.checkLike(photoBoothId, userEmail);
    }

    @Override
    public void deleteLike(Long photoBoothId, String userEmail) {
        photoBoothDeleteLikePort.deleteLike(photoBoothId, userEmail);
    }
}
