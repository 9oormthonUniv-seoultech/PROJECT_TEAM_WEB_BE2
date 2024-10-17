package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.review.ReviewCustomException;
import com.pocket.core.exception.review.ReviewErrorCode;
import com.pocket.domain.dto.review.ReviewGetRecentResponseDto;
import com.pocket.domain.dto.review.ReviewPreviewDto;
import com.pocket.domain.port.review.ReviewGetRecentPort;
import com.pocket.outbound.adapter.review.mapper.ReviewOutBoundMapper;
import com.pocket.outbound.entity.review.JpaBoothFeature;
import com.pocket.outbound.entity.review.JpaPhotoFeature;
import com.pocket.outbound.entity.review.JpaReview;
import com.pocket.outbound.entity.review.JpaReviewImage;
import com.pocket.outbound.repository.review.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetRecentAdapter implements ReviewGetRecentPort {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;
    private final BoothFeatureRepository boothFeatureRepository;
    private final PhotoFeatureRepository photoFeatureRepository;
    private final ReviewOutBoundMapper reviewOutBoundMapper;

    @Override
    public ReviewGetRecentResponseDto getRecentReview(Long photoboothId) {
        int totalReviewCount = reviewRepository.countByPhotoBoothId(photoboothId);

        List<JpaReview> recentReviews = reviewRepository.findTop2ByPhotoBoothIdOrderByIdDesc(photoboothId);

        List<ReviewPreviewDto> reviewPreviews = recentReviews.stream().map(review -> {
            List<JpaReviewImage> images = reviewImageRepository.findByReviewId(review.getId());
            String imageUrl = images.isEmpty() ? "" : images.get(0).getImage().getImageUrl();
            int imageCount = images.size();


            // Booth Features 찾기
            List<Long> boothFeatureIds = reviewBoothFeatureRepository.findBoothFeatureIdByReviewId(review.getId());
            List<JpaBoothFeature> boothFeatures = boothFeatureIds.stream()
                    .map(boothFeatureId -> boothFeatureRepository.findById(boothFeatureId)
                            .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.BOOTH_FEATURE_NOT_FOUND)))
                    .toList();

            // Photo Features 찾기
            List<Long> photoFeatureIds = reviewPhotoFeatureRepository.findPhotoFeatureIdByReviewId(review.getId());
            List<JpaPhotoFeature> photoFeatures = photoFeatureIds.stream()
                    .map(photoFeatureId -> photoFeatureRepository.findById(photoFeatureId)
                            .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.PHOTO_FEATURE_NOT_FOUND)))
                    .toList();

            List<String> descriptions = new ArrayList<>();
            // BoothFeature descriptions
            boothFeatures.forEach(boothFeature -> descriptions.add(boothFeature.getBoothFeature().getDescription()));
            // PhotoFeature descriptions
            photoFeatures.forEach(photoFeature -> descriptions.add(photoFeature.getPhotoFeature().getDescription()));


            return reviewOutBoundMapper.toReviewPreviewDto(descriptions, review, imageUrl, imageCount);
        }).collect(Collectors.toList());

        return new ReviewGetRecentResponseDto(totalReviewCount, reviewPreviews);
    }
}
