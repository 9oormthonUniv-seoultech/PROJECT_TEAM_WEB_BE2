package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;

public interface ReviewRegisterUseCase {

    ReviewRegisterResponseDto registerReviewResponse(ReviewRegisterRequestDto requestDto, String name);

}
