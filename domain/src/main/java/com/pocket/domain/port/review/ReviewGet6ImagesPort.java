package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.ReviewGet6ImagesResponseDto;

public interface ReviewGet6ImagesPort {

    ReviewGet6ImagesResponseDto get6Images(Long photoboothId);

}
