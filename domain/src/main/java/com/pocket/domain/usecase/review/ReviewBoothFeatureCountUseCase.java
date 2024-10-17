package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.BoothFeatureCountDto;

import java.util.List;

public interface ReviewBoothFeatureCountUseCase {

    List<BoothFeatureCountDto> getReviewBoothFeatures(Long photoboothId);

}
