package com.pocket.domain.service.review;


import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.port.file.FileDownloadPort;
import com.pocket.domain.port.review.*;
import com.pocket.domain.usecase.review.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class ReviewService implements ReviewRegisterUseCase, ReviewGet6ImagesUseCase, ReviewGetRecentUseCase, ReviewGetAllImagesUseCase, ReviewBoothFeatureCountUseCase, ReviewPhotoFeatureCountUseCase, ReviewGetBoothFeatureUseCase, ReviewGetPhotoFeatureUseCase, ReviewGetAllUseCase, ReviewMypageUseCase


{

    private final ReviewRegisterPort reviewRegisterPort;
    private final ReviewGet6ImagesPort reviewGet6ImagesPort;
    private final ReviewGetRecentPort reviewGetRecentPort;
    private final ReviewGetAllImagesPort reviewGetAllImagesPort;
    private final ReviewBoothFeatureCountPort reviewBoothFeatureCountPort;
    private final ReviewPhotoFeatureCountPort reviewPhotoFeatureCountPort;
    private final ReviewGetBoothFeaturePort reviewGetBoothFeaturePort;
    private final ReviewGetPhotoFeaturePort reviewGetPhotoFeaturePort;
    private final ReviewGetAllPort reviewGetAllPort;
    private final FileDownloadPort fileDownloadPort;
    private final ReviewMypagePort reviewMypagePort;

    @Override
    public ReviewRegisterResponseDto registerReviewResponse(ReviewRegisterRequestDto reviewRegisterRequestDto, String name) {
        return reviewRegisterPort.registerReview(reviewRegisterRequestDto, name);
    }

    @Override
    public ReviewGet6ImagesResponseDto get6Images(Long photoboothId) {
        ReviewGet6ImagesResponseDto response = reviewGet6ImagesPort.get6Images(photoboothId);

        List<String> presignedUrls = response.filePaths().stream()
                .map(fileDownloadPort::getDownloadPresignedUrl)
                .collect(Collectors.toList());

        return new ReviewGet6ImagesResponseDto(presignedUrls, response.totalImageCount());
    }

    @Override
    public ReviewGetResponseDto getRecentReview(Long photoboothId) {
        ReviewGetResponseDto response = reviewGetRecentPort.getRecentReview(photoboothId);

        List<ReviewPreviewDto> reviewPreviewsWithPresignedUrls = response.reviews().stream().map(review -> {
            String presignedUrl = review.imageUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(review.imageUrl());
            return new ReviewPreviewDto(
                    review.photoboothId(),
                    review.name(),
                    review.year(),
                    review.month(),
                    review.date(),
                    review.contents(),
                    review.features(),
                    presignedUrl,
                    review.imageCount()
            );
        }).collect(Collectors.toList());

        return new ReviewGetResponseDto(response.reviewCount(), reviewPreviewsWithPresignedUrls);
    }

    @Override
    public List<String> getAllImages(Long photoboothId, Pageable pageable) {
        List<String> rawImagePaths = reviewGetAllImagesPort.getAllImages(photoboothId, pageable);

        return rawImagePaths.stream()
                .map(fileDownloadPort::getDownloadPresignedUrl)
                .collect(Collectors.toList());
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

    @Override
    public ReviewGetResponseDto getAllReviews(Long photoboothId, Pageable pageable) {
        ReviewGetResponseDto response = reviewGetAllPort.getAllReviews(photoboothId, pageable);

        List<ReviewPreviewDto> reviewPreviewsWithPresignedUrls = response.reviews().stream().map(review -> {
            String presignedUrl = review.imageUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(review.imageUrl());
            return new ReviewPreviewDto(
                    review.photoboothId(),
                    review.name(),
                    review.year(),
                    review.month(),
                    review.date(),
                    review.contents(),
                    review.features(),
                    presignedUrl,
                    review.imageCount()
            );
        }).collect(Collectors.toList());

        return new ReviewGetResponseDto(response.reviewCount(), reviewPreviewsWithPresignedUrls);
    }

    @Override
    public ReviewMypageDto reviewMypage(String userEmail) {
        ReviewMypageDto response = reviewMypagePort.reviewMypage(userEmail);
        List<ReviewMypageDetailDto> reviewMypageWithPresignedUrls = response.reviewMypageDetailDtoList().stream().map(review -> {
            String presignedUrl = review.imageUrl().isEmpty() ? "" : fileDownloadPort.getDownloadPresignedUrl(review.imageUrl());
            return new ReviewMypageDetailDto(
                    review.reviewId(),
                    presignedUrl,
                    review.month(),
                    review.date(),
                    review.photoboothName(),
                    review.rating()
            );
        }) .collect(Collectors.toList());
        return new ReviewMypageDto(response.reviewCount(), reviewMypageWithPresignedUrls);
    }
}
