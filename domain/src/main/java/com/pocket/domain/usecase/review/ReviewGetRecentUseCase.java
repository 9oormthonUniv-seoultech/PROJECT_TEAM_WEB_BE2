package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewGetResponseDto;


public interface ReviewGetRecentUseCase {

    ReviewGetResponseDto getRecentReview(Long photoboohtId);

}
