package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewBoothFeatureDto;

import java.util.List;

public interface ReviewBoothFeatureUseCase {

    List<ReviewBoothFeatureDto> getReviewBoothFeatures(Long photoboothId);

}
