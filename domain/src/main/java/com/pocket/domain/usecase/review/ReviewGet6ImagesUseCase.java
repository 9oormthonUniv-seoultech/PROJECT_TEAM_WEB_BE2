package com.pocket.domain.usecase.review;


import com.pocket.domain.dto.review.ReviewGet6ImagesResponseDto;

public interface ReviewGet6ImagesUseCase {

    ReviewGet6ImagesResponseDto get6Images(Long photoboothId);

}
