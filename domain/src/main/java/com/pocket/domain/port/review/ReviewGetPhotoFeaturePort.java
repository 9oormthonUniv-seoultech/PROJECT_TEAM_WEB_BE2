package com.pocket.domain.port.review;

import com.pocket.domain.dto.review.PhotoFeatureDto;

import java.util.List;

public interface ReviewGetPhotoFeaturePort {

    List<PhotoFeatureDto> getPhotoFeatures();

}
