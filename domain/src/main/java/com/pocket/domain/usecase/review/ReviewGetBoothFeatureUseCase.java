package com.pocket.domain.usecase.review;

import com.pocket.domain.dto.review.BoothFeatureDto;

import java.util.List;

public interface ReviewGetBoothFeatureUseCase {

    List<BoothFeatureDto> getBoothFeatures();

}
