package com.pocket.domain.service.review;


import com.pocket.core.aop.annotation.DomainService;
import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;
import com.pocket.domain.port.review.ReviewRegisterPort;
import com.pocket.domain.usecase.review.ReviewRegisterUseCase;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ReviewService implements ReviewRegisterUseCase {

    private final ReviewRegisterPort reviewRegisterPort;

    @Override
    public ReviewRegisterResponseDto registerReviewResponse(ReviewRegisterRequestDto reviewRegisterRequestDto, String name) {
        return reviewRegisterPort.registerReview(reviewRegisterRequestDto, name);
    }
}
