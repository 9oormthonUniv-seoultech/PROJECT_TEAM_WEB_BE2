package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.ReviewPhotoFeatureDto;

import java.util.List;


public interface ReviewPhotoFeatureUseCase {

    List<ReviewPhotoFeatureDto> getReviewPhotoFeatures(Long photoboothId);

}
