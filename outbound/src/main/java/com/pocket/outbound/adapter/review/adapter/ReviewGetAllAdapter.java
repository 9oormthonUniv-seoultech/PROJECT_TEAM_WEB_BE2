package com.pocket.outbound.adapter.review.adapter;


import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.review.ReviewCustomException;
import com.pocket.core.exception.review.ReviewErrorCode;
import com.pocket.domain.dto.review.ReviewGetResponseDto;
import com.pocket.domain.dto.review.ReviewPreviewDto;
import com.pocket.domain.port.review.ReviewGetAllPort;
import com.pocket.outbound.adapter.review.mapper.ReviewOutBoundMapper;
import com.pocket.outbound.entity.review.JpaBoothFeature;
import com.pocket.outbound.entity.review.JpaPhotoFeature;
import com.pocket.outbound.entity.review.JpaReview;
import com.pocket.outbound.entity.review.JpaReviewImage;
import com.pocket.outbound.repository.review.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AdapterService
@RequiredArgsConstructor
public class ReviewGetAllAdapter implements ReviewGetAllPort {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;
    private final BoothFeatureRepository boothFeatureRepository;
    private final PhotoFeatureRepository photoFeatureRepository;
    private final ReviewOutBoundMapper reviewOutBoundMapper;

    @Override
    public ReviewGetResponseDto getAllReviews(Long photoboothId, Pageable pageable) {
        int totalReviewCount = reviewRepository.countByPhotoBoothId(photoboothId);

        Page<JpaReview> reviews = reviewRepository.findByPhotoBoothId(photoboothId, pageable);

        List<ReviewPreviewDto> reviewPreviews = reviews.stream()
                .map(this::createReviewPreview)
                .collect(Collectors.toList());

        return new ReviewGetResponseDto(totalReviewCount, reviewPreviews);
    }

    private ReviewPreviewDto createReviewPreview(JpaReview review) {
        String imageUrl = getFirstImageUrlForReview(review.getId());
        int imageCount = getReviewImageCount(review.getId());

        List<String> descriptions = getDescriptionsForReview(review);

        return reviewOutBoundMapper.toReviewPreviewDto(descriptions, review, imageUrl, imageCount);
    }

    private String getFirstImageUrlForReview(Long reviewId) {
        List<JpaReviewImage> images = reviewImageRepository.findByReviewId(reviewId);
        return images.isEmpty() ? "" : images.get(0).getImage().getImageUrl();
    }

    private int getReviewImageCount(Long reviewId) {
        List<JpaReviewImage> images = reviewImageRepository.findByReviewId(reviewId);
        return images.size();
    }

    private List<String> getDescriptionsForReview(JpaReview review) {
        List<String> descriptions = new ArrayList<>();

        List<Long> boothFeatureIds = reviewBoothFeatureRepository.findBoothFeatureIdByReviewId(review.getId());
        List<JpaBoothFeature> boothFeatures = getBoothFeatures(boothFeatureIds);
        boothFeatures.forEach(boothFeature -> descriptions.add(boothFeature.getBoothFeature().getDescription()));

        List<Long> photoFeatureIds = reviewPhotoFeatureRepository.findPhotoFeatureIdByReviewId(review.getId());
        List<JpaPhotoFeature> photoFeatures = getPhotoFeatures(photoFeatureIds);
        photoFeatures.forEach(photoFeature -> descriptions.add(photoFeature.getPhotoFeature().getDescription()));

        return descriptions;
    }

    private List<JpaBoothFeature> getBoothFeatures(List<Long> boothFeatureIds) {
        return boothFeatureIds.stream()
                .map(boothFeatureId -> boothFeatureRepository.findById(boothFeatureId)
                        .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.BOOTH_FEATURE_NOT_FOUND)))
                .collect(Collectors.toList());
    }

    private List<JpaPhotoFeature> getPhotoFeatures(List<Long> photoFeatureIds) {
        return photoFeatureIds.stream()
                .map(photoFeatureId -> photoFeatureRepository.findById(photoFeatureId)
                        .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.PHOTO_FEATURE_NOT_FOUND)))
                .collect(Collectors.toList());
    }


}
