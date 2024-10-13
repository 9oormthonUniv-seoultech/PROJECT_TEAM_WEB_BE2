package com.pocket.outbound.adapter.photobooth.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.util.DistanceCalculator;
import com.pocket.domain.dto.photobooth.NearPhotoBoothInfo;
import com.pocket.domain.dto.photobooth.PhotoBoothFindResponseDto;
import com.pocket.domain.entity.photobooth.PhotoBoothBrand;
import com.pocket.domain.port.photobooth.PhotoBoothFindPort;
import com.pocket.outbound.adapter.photobooth.mapper.PhotoBoothOutBoundMapper;
import com.pocket.outbound.entity.JpaPhotoBooth;
import com.pocket.outbound.repository.PhotoBoothRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothFindAdapter implements PhotoBoothFindPort {

    private final PhotoBoothRepository photoBoothRepository;
    private final PhotoBoothOutBoundMapper photoBoothOutBoundMapper;

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

    public List<NearPhotoBoothInfo> getPhotoboothWithin2Km(double currentLat, double currentLon, List<PhotoBoothBrand> brands) {

        // 모든 포토부스 데이터를 가져옵니다.
        List<JpaPhotoBooth> allPhotobooths;

        if (brands != null && !brands.isEmpty()) {
            // 지정된 브랜드 목록으로 포토부스를 필터링하여 조회
            allPhotobooths = photoBoothRepository.findByPhotoBoothPhotoBoothBrandIn(brands);
        } else {
            // 브랜드 필터링 없이 모든 포토부스 조회
            allPhotobooths = photoBoothRepository.findAll();
        }

        // 스트림을 사용하여 반경 2km 이내의 포토부스를 필터링합니다.
        List<JpaPhotoBooth> photoBoothList = allPhotobooths.stream()
                .filter(pb -> {
                    double distance = DistanceCalculator.haversineDistance(
                            currentLat, currentLon, pb.getPhotoBooth().getX(), pb.getPhotoBooth().getY()
                    );
                    return distance <= 2.0; // 반경 2km
                })
                .toList();

        return photoBoothList.stream()
                .map(photoBoothOutBoundMapper::toDTO)
                .toList();
    }

}
