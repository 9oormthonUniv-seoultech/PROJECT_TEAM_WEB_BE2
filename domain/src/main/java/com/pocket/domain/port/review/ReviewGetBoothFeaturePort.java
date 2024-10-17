package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.BoothFeatureDto;

import java.util.List;

public interface ReviewGetBoothFeaturePort {

    List<BoothFeatureDto> getBoothFeatures();

}
