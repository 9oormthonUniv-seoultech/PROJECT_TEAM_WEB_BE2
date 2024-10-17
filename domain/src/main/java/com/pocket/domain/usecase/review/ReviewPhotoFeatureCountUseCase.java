package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.PhotoFeatureCountDto;

import java.util.List;


public interface    ReviewPhotoFeatureCountUseCase {

    List<PhotoFeatureCountDto> getReviewPhotoFeatures(Long photoboothId);

}
