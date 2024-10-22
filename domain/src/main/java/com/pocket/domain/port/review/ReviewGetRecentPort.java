package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewGetResponseDto;

public interface ReviewGetRecentPort {

    ReviewGetResponseDto getRecentReview(Long photoboothId);

}
