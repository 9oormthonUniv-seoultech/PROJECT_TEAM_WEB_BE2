package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.PhotoFeatureDto;

import java.util.List;

public interface ReviewGetPhotoFeatureUseCase {

    List<PhotoFeatureDto> getPhotoFeatures();

}
