package com.pocket.outbound.adapter.photobooth.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.domain.dto.photobooth.PhotoBoothModalDto;
import com.pocket.domain.dto.review.BoothFeatureCountDto;
import com.pocket.domain.dto.review.PhotoFeatureCountDto;
import com.pocket.domain.entity.review.BoothFeature;
import com.pocket.domain.entity.review.PhotoFeature;
import com.pocket.domain.port.photobooth.PhotoBoothGetModalPort;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.entity.review.JpaBoothFeature;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import com.pocket.outbound.repository.review.ReviewBoothFeatureRepository;
import com.pocket.outbound.repository.review.ReviewImageRepository;
import com.pocket.outbound.repository.review.ReviewPhotoFeatureRepository;
import com.pocket.outbound.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AdapterService
@RequiredArgsConstructor
public class PhotoBoothGetModalAdapter implements PhotoBoothGetModalPort {

    private final PhotoBoothRepository photoBoothRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;

    @Override
    public PhotoBoothModalDto getPhotoBoothModal(Long photoboothId) {
        JpaPhotoBooth entity = photoBoothRepository.findById(photoboothId)
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        // photoboothId로 해당 포토부스의 리뷰를 다 검색 -> 거기서 개수세기
        List<Long> reviewIds = reviewRepository.findReviewIdsByPhotoBoothId(photoboothId);

        List<BoothFeatureCountDto> boothFeatures = reviewBoothFeatureRepository.findTopBoothFeaturesByReviewIds(reviewIds);
        Optional<BoothFeatureCountDto> boothFeature =  boothFeatures.stream().findFirst();

        List<PhotoFeatureCountDto> photoFeatures = reviewPhotoFeatureRepository.findTopPhotoFeaturesByReviewIds(reviewIds);
        Optional<PhotoFeatureCountDto> photoFeature =  photoFeatures.stream().findFirst();

        List<String> features = new ArrayList<>();
        boothFeature.ifPresent(feature -> features.add(feature.featureName()));
        photoFeature.ifPresent(feature -> features.add(feature.featureName()));

        int imageCount = reviewImageRepository.countByReviewPhotoBoothId(photoboothId);
        int reviewCount = reviewRepository.countByPhotoBoothId(photoboothId);

        return new PhotoBoothModalDto(
                entity.getPhotoBooth().getName(),
                entity.getPhotoBooth().getX(),
                entity.getPhotoBooth().getY(),
                features,
                entity.getPhotoBooth().getAverageRating(),
                imageCount,
                reviewCount
        );
    }
}
