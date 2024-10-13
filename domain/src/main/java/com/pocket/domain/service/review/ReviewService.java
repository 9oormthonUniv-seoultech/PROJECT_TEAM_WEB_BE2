package com.pocket.domain.service.review;


import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.review.*;
import com.pocket.domain.port.review.ReviewGet6ImagesPort;
import com.pocket.domain.port.review.ReviewGetRecentPort;
import com.pocket.domain.port.review.ReviewRegisterPort;
import com.pocket.domain.usecase.review.ReviewGet6ImagesUseCase;
import com.pocket.domain.usecase.review.ReviewGetRecentUseCase;
import com.pocket.domain.usecase.review.ReviewRegisterUseCase;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ReviewService implements ReviewRegisterUseCase, ReviewGet6ImagesUseCase, ReviewGetRecentUseCase {

    private final ReviewRegisterPort reviewRegisterPort;
    private final ReviewGet6ImagesPort reviewGet6ImagesPort;
    private final ReviewGetRecentPort reviewGetRecentPort;

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
}
