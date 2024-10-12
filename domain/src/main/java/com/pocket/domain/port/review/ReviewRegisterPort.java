package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewRegisterRequestDto;
import com.pocket.domain.dto.review.ReviewRegisterResponseDto;

public interface ReviewRegisterPort {

    ReviewRegisterResponseDto registerReview(ReviewRegisterRequestDto requestDto, String name);

}
