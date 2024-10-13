package com.pocket.domain.service.review;


import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.port.review.ReviewGet6ImagesPort;
import com.pocket.domain.port.review.ReviewGetAllImagesPort;
import com.pocket.domain.port.review.ReviewGetRecentPort;
import com.pocket.domain.port.review.ReviewRegisterPort;
import com.pocket.domain.usecase.review.ReviewGet6ImagesUseCase;
import com.pocket.domain.usecase.review.ReviewGetAllImagesUseCase;
import com.pocket.domain.usecase.review.ReviewGetRecentUseCase;
import com.pocket.domain.usecase.review.ReviewRegisterUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class ReviewService implements ReviewRegisterUseCase, ReviewGet6ImagesUseCase, ReviewGetRecentUseCase, ReviewGetAllImagesUseCase {

    private final ReviewRegisterPort reviewRegisterPort;
    private final ReviewGet6ImagesPort reviewGet6ImagesPort;
    private final ReviewGetRecentPort reviewGetRecentPort;
    private final ReviewGetAllImagesPort reviewGetAllImagesPort;

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
}
