package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewGetRecentResponseDto;


public interface ReviewGetRecentUseCase {

    ReviewGetRecentResponseDto getRecentReview(Long photoboohtId);

}
