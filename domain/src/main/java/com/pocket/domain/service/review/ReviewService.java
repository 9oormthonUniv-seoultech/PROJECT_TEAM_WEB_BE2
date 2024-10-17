package com.pocket.domain.service.review;


import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.port.review.*;
import com.pocket.domain.usecase.review.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class ReviewService implements ReviewRegisterUseCase, ReviewGet6ImagesUseCase, ReviewGetRecentUseCase, ReviewGetAllImagesUseCase, ReviewBoothFeatureCountUseCase, ReviewPhotoFeatureCountUseCase, ReviewGetBoothFeatureUseCase, ReviewGetPhotoFeatureUseCase

{

    private final ReviewRegisterPort reviewRegisterPort;
    private final ReviewGet6ImagesPort reviewGet6ImagesPort;
    private final ReviewGetRecentPort reviewGetRecentPort;
    private final ReviewGetAllImagesPort reviewGetAllImagesPort;
    private final ReviewBoothFeatureCountPort reviewBoothFeatureCountPort;
    private final ReviewPhotoFeatureCountPort reviewPhotoFeatureCountPort;
    private final ReviewGetBoothFeaturePort reviewGetBoothFeaturePort;
    private final ReviewGetPhotoFeaturePort reviewGetPhotoFeaturePort;

    @Override
    public ReviewRegisterResponseDto registerReviewResponse(ReviewRegisterRequestDto reviewRegisterRequestDto, String name) {
        return reviewRegisterPort.registerReview(reviewRegisterRequestDto, name);
    }

    @Override
    public ReviewGet6ImagesResponseDto get6Images(Long photoboothId) {
        return reviewGet6ImagesPort.get6Images(photoboothId);
    }

    @Override
    public ReviewGetRecentResponseDto getRecentReview(Long photoboothId) {
        return reviewGetRecentPort.getRecentReview(photoboothId);
    }

    @Override
    public List<String> getAllImages(Long photoboothId) {
        return reviewGetAllImagesPort.getAllImages(photoboothId);
    }

    @Override
    public List<BoothFeatureCountDto> getReviewBoothFeatures(Long photoboothId) {
        return reviewBoothFeatureCountPort.getReviewBoothFeature(photoboothId);
    }

    @Override
    public List<PhotoFeatureCountDto> getReviewPhotoFeatures(Long photoboothId) {
        return reviewPhotoFeatureCountPort.getReviewPhotoFeature(photoboothId);
    }

    @Override
    public List<BoothFeatureDto> getBoothFeatures() {
        return reviewGetBoothFeaturePort.getBoothFeatures();
    }

    @Override
    public List<PhotoFeatureDto> getPhotoFeatures() {
        return reviewGetPhotoFeaturePort.getPhotoFeatures();
    }
}
