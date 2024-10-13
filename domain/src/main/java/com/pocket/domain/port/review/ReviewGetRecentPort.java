package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewGetRecentResponseDto;

public interface ReviewGetRecentPort {

    ReviewGetRecentResponseDto getRecentReview(Long photoboothId);

}
