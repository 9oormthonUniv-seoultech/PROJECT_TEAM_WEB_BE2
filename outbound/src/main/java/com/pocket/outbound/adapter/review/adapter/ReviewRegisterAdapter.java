package com.pocket.outbound.adapter.review.adapter;

import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import com.pocket.core.exception.review.ReviewCustomException;
import com.pocket.core.exception.review.ReviewErrorCode;
import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;
import com.pocket.domain.entity.image.Image;
import com.pocket.domain.entity.image.ImageType;
import com.pocket.domain.port.review.ReviewRegisterPort;
import com.pocket.outbound.adapter.review.mapper.ReviewOutBoundMapper;
import com.pocket.outbound.entity.photobooth.JpaPhotoBooth;
import com.pocket.outbound.entity.review.*;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.repository.*;
import com.pocket.outbound.repository.photobooth.PhotoBoothRepository;
import com.pocket.outbound.repository.review.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AdapterService
@RequiredArgsConstructor
public class ReviewRegisterAdapter implements ReviewRegisterPort {

    private final PhotoBoothRepository photoBoothRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewOutBoundMapper reviewOutBoundMapper;
    private final ReviewImageRepository reviewImageRepository;
    private final BoothFeatureRepository boothFeatureRepository;
    private final PhotoFeatureRepository photoFeatureRepository;
    private final ReviewBoothFeatureRepository reviewBoothFeatureRepository;
    private final ReviewPhotoFeatureRepository reviewPhotoFeatureRepository;



    @Override
    public ReviewRegisterResponseDto registerReview(ReviewRegisterRequestDto dto, String name) {

        JpaPhotoBooth photoBooth = photoBoothRepository.findById(dto.photoboothId())
                .orElseThrow(() -> new PhotoBoothCustomException(PhotoBoothErrorCode.PHOTOBOOTH_NOT_FOUND));

        JpaUser jpaUser = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        JpaReview reviewEntity = reviewOutBoundMapper.toJpaReview(dto, photoBooth, jpaUser);
        reviewRepository.save(reviewEntity);

        List<String> boothFeatures = new ArrayList<>();
        List<String> photoFeatures = new ArrayList<>();

        for (Long boothFeatureId : dto.boothFeatures()) {
            JpaBoothFeature boothFeature = boothFeatureRepository.findById(boothFeatureId)
                    .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.BOOTH_FEATURE_NOT_FOUND));
            boothFeatures.add(boothFeature.getBoothFeature().getDescription());
            JpaReviewBoothFeature reviewBoothFeature = JpaReviewBoothFeature.of(reviewEntity, boothFeature);
            reviewBoothFeatureRepository.save(reviewBoothFeature);
        }

        for (Long photoFeatureId : dto.photoFeatures()) {
            JpaPhotoFeature photoFeature = photoFeatureRepository.findById(photoFeatureId)
                    .orElseThrow(() -> new ReviewCustomException(ReviewErrorCode.PHOTO_FEATURE_NOT_FOUND));
            photoFeatures.add(photoFeature.getPhotoFeature().getDescription());
            JpaReviewPhotoFeature reviewPhotoFeature = JpaReviewPhotoFeature.of(reviewEntity, photoFeature);
            reviewPhotoFeatureRepository.save(reviewPhotoFeature);
        }


        for (String filePath : dto.filePaths()) {
            Image image = new Image(ImageType.REVIEW);
            image.makeReviewImage(filePath);
            JpaReviewImage reviewImage = JpaReviewImage.builder()
                    .image(image)
                    .review(reviewEntity)
                    .build();

            reviewImageRepository.save(reviewImage);
        }

        // 포토부스의 별점 업데이트
        photoBooth.updateRating(dto.rating());
        photoBoothRepository.save(photoBooth);

        return new ReviewRegisterResponseDto(
                dto.photoboothId(),
                dto.rating(),
                boothFeatures,
                photoFeatures,
                dto.filePaths(),
                dto.content()
        );
    }
}
